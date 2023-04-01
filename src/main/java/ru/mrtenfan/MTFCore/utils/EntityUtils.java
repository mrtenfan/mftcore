package ru.mrtenfan.MTFCore.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;

public class EntityUtils {
	
	public static ArrayList<CustomEntity> entities = new ArrayList<CustomEntity>();

	public static void updateListEntities() {
		entities.clear();
		@SuppressWarnings("unchecked")
		Map<Integer, Class<? extends Entity>> entityIDMap = EntityList.IDtoClassMapping;
		
		for(int id : entityIDMap.keySet()) {
			Class<? extends Entity> entity = entityIDMap.get(id);
			if(isEntitySuperClassEquals(entity, EntityLiving.class)) {
				String name = EntityList.getStringFromID(id);
				entities.add(new CustomEntity(entity, name, id));
			}
		}
		
		entities.sort(new Comparator<CustomEntity>() {

			@Override
			public int compare(CustomEntity e1, CustomEntity e2) {
				return (e1.getId() < e2.getId() ? -1 : 1);
			}
		});
	}
	
	public static boolean isEntitySuperClassEquals(Class<? extends Entity> entity, Class<?> clazz) {
		Class<?> smthclazz = entity.getSuperclass();
		boolean flag1 = false;
		while(smthclazz != null) {
			flag1 = smthclazz.equals(clazz);
			smthclazz = smthclazz.getSuperclass();
			if(flag1)
				break;
		}
		return flag1;
	}

	public static int getNumberOfEntities() {
		return entities.size();
	}
	
	public static class CustomEntity {
		
		private Class<? extends Entity> entity;
		private String name;
		private int id;
		
		public CustomEntity(Class<? extends Entity> entity, String name, int id) {
			this.entity = entity;
			this.name = name;
			this.id = id;
		}

		public Class<? extends Entity> getEntityClass() {
			return entity;
		}

		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}
		
		@Override
		public String toString() {
			return "Entity: " + getName() + ", ID: " + getId() + ", EntityClass: " + getEntityClass().getName() + ";";
		}
	}
}