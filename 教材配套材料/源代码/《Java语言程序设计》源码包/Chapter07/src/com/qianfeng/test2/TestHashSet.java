package com.qianfeng.test2;

import java.util.HashSet;
import java.util.Iterator;

class CatFood{
	String prand;
	String type;
	double price;

	public CatFood() {
	}

	public CatFood(String prand, String type, double price) {
		this.prand = prand;
		this.type = type;
		this.price = price;
	}

	public String getPrand() {
		return prand;
	}

	public void setPrand(String prand) {
		this.prand = prand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
public class TestHashSet{
	public static void main(String[] args) {
		HashSet<CatFood> shoppingCart = new HashSet<>();//创建购物车
		//创建猫粮对象
		CatFood food1 = new CatFood("roy","幼猫粮",109.5);
		CatFood food2 = new CatFood("now","幼猫粮",89.5);
		CatFood food3 = new CatFood("go","成猫粮",180.5);
		//将猫粮添加到购物车
		shoppingCart.add(food1);
		shoppingCart.add(food2);
		shoppingCart.add(food3);
		Iterator<CatFood> it = shoppingCart.iterator(); // 创建迭代器
		System.out.println("您的购物车里的商品信息：\n品牌\t\t类型\t\t\t价格");
		System.out.println("———————————————————————————————————————");
		while (it.hasNext()) {// 判断购物车中是否有元素
			System.out.println(it.next()); // 输出购物车中的商品
		}
		System.out.println("———————————————————————————————————————");
		double sumMoney = food1.getPrice() + food2.getPrice() + food3.getPrice(); // 求猫粮总价
		System.out.println("合计：\t\t\t" + sumMoney + "元\n\t\t\t—→点我去结账"); // 输出猫粮总价
	}
}