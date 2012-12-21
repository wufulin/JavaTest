package com.wufulin.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wufulin.orm.entity.ShareGoodsLike;

public class ItemMatrix {

	private Map<Integer,Set<Integer>> userGoodsMap=new HashMap<Integer, Set<Integer>>();	// 用户--商品映射表
	private Map<Integer,Set<Integer>> goodsUsersMap=new HashMap<Integer,Set<Integer>>();	// 商品--用户映射表
	
	/**
	 * 计算物品相似度矩阵
	 * @return
	 */
	public Map<Integer,List<ItemSimilar>> calculateItemSimilarity(){
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
		Map<Integer,List<ItemSimilar>> w=new HashMap<Integer,List<ItemSimilar>>();
		for(Integer i:c.keySet()){
			Map<Integer,Integer> iMap=c.get(i);
			if(!w.containsKey(i)){
				w.put(i, new ArrayList<ItemSimilar>());
			}
			List<ItemSimilar> itemSimilars=w.get(i);
			int nu=userGoodsMap.get(i).size();
			for(Integer j:iMap.keySet()){
				int nv=userGoodsMap.get(j).size();
				double value=iMap.get(j)/Math.sqrt(nv*nu);
				ItemSimilar itemSimilar=new ItemSimilar(j, value);
				itemSimilars.add(itemSimilar);
			}
		}
		
		for(Integer i:w.keySet()){
			System.out.print("User "+i+" --> ");
			List<ItemSimilar> itemSimilars=w.get(i);
			for(ItemSimilar item:itemSimilars){
				System.out.print(item.getGoodsId()+" = "+item.getSimilar()+"\t");
			}
			System.out.println();
		}
		
		// return the matrix W
		return w;
	}
}
