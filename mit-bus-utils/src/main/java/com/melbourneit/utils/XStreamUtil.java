package com.melbourneit.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.XStream;

public class XStreamUtil {
	
	public static <T> String toXML(T obj, Class<?> clz) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
	    return xstream.toXML(obj);
	}
	
	public static <T> String toXML(T obj, Class<?> clz, String alias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
	    return xstream.toXML(obj);
	}
	
	public static <T> String toXML(Class<?> clz, List<?> list) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		xstream.alias(clz.getSimpleName()+"List", List.class);		
	    return xstream.toXML(list);
	}
	
	public static <T> String toXML(Class<?> clz, String alias, List<?> list, String listAlias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
		xstream.alias(listAlias, List.class);		
	    return xstream.toXML(list);
	}
	
	public static <T> String toXML(Class<?> clz, Set<?> set) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		xstream.alias(clz.getSimpleName()+"Set", Set.class);		
	    return xstream.toXML(set);
	}
	
	public static <T> String toXML(Class<?> clz, String alias, Set<?> set, String listAlias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
		xstream.alias(listAlias, Map.class);		
	    return xstream.toXML(set);
	}
	
	public static <T> String toXML(Class<?> clz, Map<String, ?> map) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		xstream.alias(clz.getSimpleName()+"Map", Map.class);		
	    return xstream.toXML(map);
	}
	
	public static <T> String toXML(Class<?> clz, String alias, Map<String, ?> map, String listAlias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
		xstream.alias(listAlias, Map.class);		
	    return xstream.toXML(map);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXML(String xml, Class<?> clz){
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		return (T) xstream.fromXML(xml);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXML(String xml, Class<?> clz, String alias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
	    return (T) xstream.fromXML(xml);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXMLList(String xml, Class<?> clz) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		xstream.alias(clz.getSimpleName()+"List", List.class);		
	    return (T) xstream.fromXML(xml);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXMLList(String xml, Class<?> clz, String alias, String listAlias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
		xstream.alias(listAlias, List.class);		
	    return (T) xstream.fromXML(xml);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXMLSet(String xml, Class<?> clz) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		xstream.alias(clz.getSimpleName()+"Set", Set.class);		
	    return (T) xstream.toXML(xml);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXMLSet(String xml, Class<?> clz, String alias, String setAlias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
		xstream.alias(setAlias, Set.class);		
	    return (T) xstream.toXML(xml);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromXMLMap(String xml, Class<?> clz) {
		XStream xstream = new XStream();
		xstream.alias(clz.getSimpleName(), clz);
		xstream.alias(clz.getSimpleName()+"Map", Map.class);		
	    return (T) xstream.toXML(xml);
	}
	
	public static <T> String fromXMLMap(String xml, Class<?> clz, String alias, String mapAlias) {
		XStream xstream = new XStream();
		xstream.alias(alias, clz);
		xstream.alias(mapAlias, Map.class);		
	    return xstream.toXML(xml);
	}
}
