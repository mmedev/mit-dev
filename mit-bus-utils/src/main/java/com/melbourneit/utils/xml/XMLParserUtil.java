package com.melbourneit.utils.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Hashtable;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.tools.MappingTool;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class XMLParserUtil {
	public static XMLContextWrapper defaultXmlContext;
	private static final Logger LOG = LoggerFactory.getLogger(XMLParserUtil.class);
	private static Hashtable<String,XMLContext> xmlContextsHash = new Hashtable<String,XMLContext>();

	static {
		try {
			defaultXmlContext = new XMLContextWrapper();
			defaultXmlContext.addPackage("com.melbourneit.utils.provision.data.*");
		} catch (Exception e) {
			LOG.debug("XMLParserUtil Exception: " + e);
		}
	}

	public static String getXMLStr(Object obj) throws Exception {
		String xmlStr = "";

		Marshaller marshaller = defaultXmlContext.createMarshaller();
		StringWriter writer = new StringWriter();
		marshaller.setWriter(writer);
		marshaller.marshal(obj);
		xmlStr = writer.toString();

		return xmlStr;
	}

	public static String getXMLStr(Object obj, String mappingFile) 
	    throws Exception 
	{
	    return getXMLStr(obj,getXmlContext(mappingFile));
	}

	public static String getXMLStr(Object obj, XMLContext xmlContext) 
	    throws IOException, MarshalException, ValidationException
	{
        String xmlStr = "";

        Marshaller marshaller = xmlContext.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.setWriter(writer);
        marshaller.marshal(obj);
        xmlStr = writer.toString();

        return xmlStr;
	}
	        
	public static Object getObject(String xmlString, Class className) throws Exception {
		Object obj = null;

		Unmarshaller unmarshaller = defaultXmlContext.createUnmarshaller();
		StringReader reader = new StringReader(xmlString);
		obj = (Object)unmarshaller.unmarshal(className, reader);

		return obj;
	}

	public static Object getObject(String xmlString, XMLContext xmlContext)
       throws IOException, MappingException, MarshalException, ValidationException
    {
        Unmarshaller unmar = xmlContext.createUnmarshaller();
        
        return unmar.unmarshal(new InputSource(new StringReader(xmlString)));
    }

	public static Object getObject(String xmlString, String mappingFile) 
	    throws IOException, MappingException, MarshalException, ValidationException
	{
	    Object result = null;
	    XMLContext xmlContext = getXmlContext(mappingFile);
	    Unmarshaller unmar = xmlContext.createUnmarshaller();
	    
	    result = unmar.unmarshal(new InputSource(new StringReader(xmlString)));
	    return  result;
	}
	
	public static XMLContext createXmlContextForMappingFile(String mappingFile) 
	    throws IOException, MappingException
	{
	    XMLContext result = null;
	    
        result = new XMLContext();
        Mapping xmlMapping = new Mapping(Thread.currentThread().getContextClassLoader());
        xmlMapping.loadMapping(Thread.currentThread().getContextClassLoader().getResource(mappingFile));
        result.addMapping(xmlMapping);

        return result;    
	}

	public void generateMappingFileForClass(String className, String outputFileName) throws Exception
	{
	    String[] classNamesArray = {className};
	    generateMappingFileForClasses(classNamesArray,outputFileName);
	}

	public static void generateMappingFileForClass(Class className, String outputFileName) throws Exception
	{
        Class[] classNamesArray = {className};
        generateMappingFileForClasses(classNamesArray,outputFileName);
	}

	public static void generateMappingFileForClasses(String[] classNamesArray, String outputFileName) throws Exception
	{
	    XMLContext context = new XMLContext();
	    MappingTool mappingTool = context.createMappingTool();

	    for (String className : classNamesArray)
	    {
	        mappingTool.addClass(className);
	    }
	    
	    generateMappingFile(mappingTool, outputFileName);
	}

	public static void generateMappingFileForClasses(Class[] classNamesArray, String outputFileName) throws Exception
	{
        XMLContext context = new XMLContext();
        MappingTool mappingTool = context.createMappingTool();

	    for (Class className : classNamesArray)
	    {
	        mappingTool.addClass(className);
	    }

	    generateMappingFile(mappingTool, outputFileName);
	}

	private static void generateMappingFile(MappingTool mappingTool, String outputFileName) throws Exception
	{
	    OutputStream file = new FileOutputStream(outputFileName);
	    Writer writer = new OutputStreamWriter(file);
	    mappingTool.write(writer);
	    writer.close();
	    file.close();
	}
	
	private static XMLContext getXmlContext(String mappingFile) 
	    throws IOException, MappingException
	{
	    XMLContext result = null;
	    synchronized(xmlContextsHash)
	    {
	        result = xmlContextsHash.get(mappingFile);
	        if (result == null)
	        {
	            result = createXmlContextForMappingFile(mappingFile);
	            //ensure we add it to the hash
	            xmlContextsHash.put(mappingFile,result);
	        }
	    }
	    
	    return result;
	}
	
    public static void main(String[] args)
    {
        String classToMap = "java.lang.String";
        String mappingfileName = System.getProperty("MappingFileName","/tmp/mapping.xml");
        
        try
        {
            MappingTool tool = new XMLContext().createMappingTool();
            if (args.length > 0)
            {
                for (String classNameString : args)
                {
                    tool.addClass(Class.forName(classNameString));
                }
            }
            else
            {
                tool.addClass(classToMap);
            }
            
            Writer fileWriter = new OutputStreamWriter(new FileOutputStream(mappingfileName));
            
            tool.write(fileWriter);
        }
        catch (Throwable e)
        {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

}
