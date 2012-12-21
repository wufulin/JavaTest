package com.wufulin.orm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wufulin.orm.entity.ShareGoodsLike;
import com.wufulin.util.SetsUtil;

public class TestShareGoodsLike {

	public static final int K=3;	// 兴趣最接近的K个用户
	public static final int TOPN=2;	// 推荐TOPN个商品
	private Map<Integer,Set<Integer>> userGoodsMap=new HashMap<Integer, Set<Integer>>();	// 用户--商品映射表
	private Map<Integer,Set<Integer>> goodsUsersMap=new HashMap<Integer,Set<Integer>>();	// 商品--用户映射表
	
	/**
	 * 计算用户相似度矩阵
	 * @return
	 */
	public Map<Integer,List<UserSimilar>> calculateUserSimilarity(){
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory=new Configuration()
										.configure()
										.buildSessionFactory();

		Session session=sessionFactory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<ShareGoodsLike> result=(List<ShareGoodsLike>)session.createQuery("from ShareGoodsLike where commentType=0 order by userid").list();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("----- user-goods-matrix -----");
		for(ShareGoodsLike shareGoodsLike:result){
			int userId=shareGoodsLike.getUserId();
			int goodsId=shareGoodsLike.getGoodsId();
			if(userGoodsMap.containsKey(userId)){
				userGoodsMap.get(userId).add(goodsId);
			}else{
				Set<Integer> goodsLikes=new TreeSet<Integer>();
				goodsLikes.add(goodsId);
				userGoodsMap.put(userId, goodsLikes);
			}
		}
		
		for(Integer userId:userGoodsMap.keySet()){
			System.out.print("user: "+userId+" like: ");
			Set<Integer> goodsSet=userGoodsMap.get(userId);
			System.out.println(goodsSet.toString());
		}
		
		System.out.println("----- goods-users-matrix -----");
		for(Integer userId:userGoodsMap.keySet()){
			Set<Integer> goodsSet=userGoodsMap.get(userId);
			for(Integer goodsId:goodsSet){
				if(goodsUsersMap.containsKey(goodsId)){
					goodsUsersMap.get(goodsId).add(userId);
				}else{
					Set<Integer> users=new TreeSet<Integer>();
					users.add(userId);
					goodsUsersMap.put(goodsId, users);
				}
			}
		}
		
		for(Integer goodsId:goodsUsersMap.keySet()){
			System.out.print("goodsId: "+goodsId+" --> who like: ");
			Set<Integer> users=goodsUsersMap.get(goodsId);
			System.out.println(users.toString());
		}
		
		System.out.println("----- calculate co-rated items between users -----");
		Map<Integer,Map<Integer,Integer>> c=new HashMap<Integer,Map<Integer,Integer>>();
		
		for(Integer goodsId:goodsUsersMap.keySet()){
			Set<Integer> users=goodsUsersMap.get(goodsId);
			for(Integer i:users){
				for(Integer j:users){
					if(i==j)
						continue;
					else{
						if(c.containsKey(i)){
							Map<Integer,Integer> tempMap=c.get(i);
							if(tempMap.containsKey(j)){
								Integer value=tempMap.get(j)+1;
								tempMap.put(j, value);
							}else{
								tempMap.put(j, 1);
							}
						}else{
							Map<Integer,Integer> iMap=new HashMap<Integer,Integer>();
							iMap.put(j, 1);
							c.put(i, iMap);
						}
					}
				}
			}
		}
		
		for(Integer i:c.keySet()){
			System.out.print("User "+i+" --> ");
			Map<Integer,Integer> iMap=c.get(i);
			for(Integer j:iMap.keySet()){
				System.out.print(j+" = "+iMap.get(j)+"\t");
			}
			System.out.println();
		}
		
		// calculate final similarity matrix W
		System.out.println("----- calculate final similarity matrix W -----");
		Map<Integer,List<UserSimilar>> w=new HashMap<Integer,List<UserSimilar>>();
		for(Integer i:c.keySet()){
			Map<Integer,Integer> iMap=c.get(i);
			if(!w.containsKey(i)){
				w.put(i, new ArrayList<UserSimilar>());
			}
			List<UserSimilar> userSimilars=w.get(i);
			int nu=userGoodsMap.get(i).size();
			for(Integer j:iMap.keySet()){
				int nv=userGoodsMap.get(j).size();
				double value=iMap.get(j)/Math.sqrt(nv*nu);
				UserSimilar userSimilar=new UserSimilar(j, value);
				userSimilars.add(userSimilar);
			}
		}
		
		for(Integer i:w.keySet()){
			System.out.print("User "+i+" --> ");
			List<UserSimilar> userSimilars=w.get(i);
			for(UserSimilar us:userSimilars){
				System.out.print(us.getUserId()+" = "+us.getSimilar()+"\t");
			}
			System.out.println();
		}
		
		// return the matrix W
		return w;
	}

	/**
	 * 找出与用户相似度最大的K个用户
	 * @param userId 用户ID
	 * @param k	表示查找k个用户
	 * @return
	 */
	public List<UserSimilar> findKSimilarUsers(int userId,int k){
		List<UserSimilar> kSimilarUsers=new ArrayList<UserSimilar>();
		Map<Integer,List<UserSimilar>> w=calculateUserSimilarity();
		List<UserSimilar> userSimilars=w.get(userId);
		Collections.sort(userSimilars, new UserSimilarComparator());
		for(UserSimilar us:userSimilars){
			if(kSimilarUsers.size()>=k){
				break;
			}
			kSimilarUsers.add(us);
		}
		return kSimilarUsers;
	}
	
	/**
	 * 向指定用户推荐topN个商品
	 * @param userId 用户ID
	 * @param topN	推荐商品的数量
	 * @return
	 * @throws Exception
	 */
	public List<ItemSimilar> recommend(int userId,int topN) throws Exception{
		List<UserSimilar> us=findKSimilarUsers(userId, K);
		Set<Integer> setsUnion=null;
		
		for(UserSimilar u:us){
			Set<Integer> sA=userGoodsMap.get(u.getUserId());
			setsUnion=SetsUtil.union(sA, setsUnion);
		}
		
		Set<Integer> setUserGoods=userGoodsMap.get(userId);
		Set<Integer> setDiff=SetsUtil.difference(setUserGoods, setsUnion);
		
		List<ItemSimilar> recommendList=new ArrayList<ItemSimilar>();
		for(Integer id:setDiff){
			ItemSimilar item=new ItemSimilar(id);
			double pi=0.0;
			for(UserSimilar u:us){
				Set<Integer> userGoods=userGoodsMap.get(u.getUserId());
				if(userGoods.contains(id)){
					pi+=u.getSimilar();
				}
			}
			item.setSimilar(pi);
			recommendList.add(item);
		}
		
		Collections.sort(recommendList, new ItemSimilarComparator());
		
		List<ItemSimilar> topNRecommendations=new ArrayList<ItemSimilar>();
		for(ItemSimilar	item:recommendList){
			if(topNRecommendations.size()>=topN){
				break;
			}
			topNRecommendations.add(item);
		}
		return topNRecommendations;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TestShareGoodsLike test=new TestShareGoodsLike();
		try {
			Collection<Integer> list=Arrays.asList(35,33,39,99,36,37,90);
			for (Integer userId:list) {
				List<ItemSimilar> topNRecommends = test.recommend(userId, 2);
				for (ItemSimilar item : topNRecommends) {
					System.out.println(" 用户" + userId + "可能喜欢: "
							+ item.getGoodsId() + " \t兴趣度: "
							+ item.getSimilar());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
