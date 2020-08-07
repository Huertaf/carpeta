package es.caib.carpeta.pluginsib.carpetafront.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.utils.AbstractPluginPropertiesTranslations;

/**
 * 
 * @author anadal
 * 
 * TODO revisar noms i variables amb SCANWEB
 *
 */
public abstract class AbstractPluginFullUtilities extends AbstractPluginPropertiesTranslations {

    protected Logger log = Logger.getLogger(this.getClass());
    
    
    
    /**
     * 
     */
    public AbstractPluginFullUtilities() {
      super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public AbstractPluginFullUtilities(String propertyKeyBase, Properties properties) {
      super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public AbstractPluginFullUtilities(String propertyKeyBase) {
      super(propertyKeyBase);
    }
    
    
    public abstract String getTitle(Locale locale);

    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- HTML UTILS BUTTON -------------------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------

    // TODO XYZ mogut a base
    
    protected void sendRedirect(HttpServletResponse response, String url) {
      try {
        response.sendRedirect(url);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }

    protected final PrintWriter generateHeader(HttpServletRequest request,
        HttpServletResponse response, String absolutePluginRequestPath,
        String relativePluginRequestPath, Locale languageUI) {

      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html");
      PrintWriter out;
      try {
        out = response.getWriter();
      } catch (IOException e) {
        return null;
      }

      String lang = languageUI.getLanguage();

      out.println("<!DOCTYPE html>");
      out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"" + lang
          + "\"  lang=\"" + lang + "\">");
      out.println("<head>");

      out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;\" charset=\"UTF-8\" >");

      out.println("<title>" + getTitle(languageUI) + "</title>");
      out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

      // Javascript i CSS externs
      getJavascriptCSS(request, absolutePluginRequestPath, relativePluginRequestPath, out,
          languageUI);

      out.println("</head>");
      out.println("<body>");

      return out;

    }

    protected final void generateFooter(PrintWriter out) {
      out.println("</body>");
      out.println("</html>");
    }

    

    protected abstract void getJavascriptCSS(HttpServletRequest request,
        String absolutePluginRequestPath, String relativePluginRequestPath, PrintWriter out,
        Locale languageUI);


    

    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    // ------------------- UPLOAD FILES UTILS ------------------------------------
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    
   // TODO XYZ Llegir de AbstractWebPlugin
    
    /**
     * 
     * @param request
     * @param response
     * @return null when error then you must call to "return;"
     */
    protected Map<String, FileItem> readFilesFromRequest(HttpServletRequest request,
        HttpServletResponse response) {
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);

      try {

        if (!isMultipart) {
          throw new Exception("Form is not Multipart !!!!!!!");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();

        
        File temp= getTempDir();
        factory.setRepository(temp);
        
        
        

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request to get file items.
        @SuppressWarnings("unchecked")
        List<FileItem> fileItems = upload.parseRequest(request);

        Map<String, FileItem> mapFile = new HashMap<String, FileItem>();

        // Process the uploaded file items
        for (FileItem fi : fileItems) {

          if (!fi.isFormField()) {
            String fieldName = fi.getFieldName();
            if (log.isDebugEnabled()) {
              log.debug("Uploaded File:  PARAM = " + fieldName + " | FILENAME: "
                + fi.getName());
            }

            mapFile.put(fieldName, fi);

          }
        }

        return mapFile;

      } catch (Exception e) {
        String msg = e.getMessage();
        log.error(msg, e);
        // No emprar ni 404 ni 403
        try {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg); // bad
                                                                       // request
        } catch (Exception ee) {
          log.error(ee.getMessage(), ee);
        }
        return null; // = ERROR
      }

    }
    
    
    public static File tempDir = null;
    
    protected synchronized File getTempDir() throws IOException {
      
      if (tempDir == null) {
        File temp = File.createTempFile("test", "test");
        
        tempDir = temp.getParentFile();
              
        if (!temp.delete()) {
          temp.deleteOnExit();
        }
      }
      
      return tempDir;
      
    }
    
    
    
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    // ------------------- GESTIO D'ERRORS ---------------------------------------
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    
    // TODO XYZ mogut a base
    
    protected static final String ABSTRACT_SCAN_WEB_RES_BUNDLE = "scanwebapi";
    

    public void requestTimeOutError(String absolutePluginRequestPath,
        String relativePluginRequestPath, String query, String scanWebID,
        HttpServletRequest request, HttpServletResponse response,
        String titol) {
      String str = allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, scanWebID);

      // TODO Traduir
      // El procés de s'escaneig amb ID " + scanWebID  + " ha caducat. Torni a
      // intentar-ho.\n" + str;
      Locale locale = request.getLocale();

      String msg = getTraduccio(ABSTRACT_SCAN_WEB_RES_BUNDLE, "timeout.error", locale, getTitle(locale));

      log.error(msg + "\n" + str);

      // No emprar ni 404 ni 403
      try {
        response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, msg); // Timeout
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }


    public void requestNotFoundError(String titol, String absolutePluginRequestPath,
        String relativePluginRequestPath, String query, 
        String ID, HttpServletRequest request, HttpServletResponse response,
        Locale locale) {
      
   // No emprar ni 404 ni 403
      int httpStatusCode = HttpServletResponse.SC_BAD_REQUEST;
   // S'ha realitzat una petició al plugin [{0}] però no s'ha trobat cap mètode
      // per processar-la {1}
      
      String str = allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, ID);
      
      String msg = getTraduccio(ABSTRACT_SCAN_WEB_RES_BUNDLE, "notfound.error", locale, getTitle(locale),
          str);
      
      
      

      log.error(msg);
      
      try {
        response.sendError(httpStatusCode, msg); // bad request
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }

    
    // ---------------------------------------------------------
    // ------------------- READ LOCAL RESOURCES  ---------------
    // ---------------------------------------------------------

    // TODO XYZ mogut a base
    public void retornarRecursLocal(String absolutePluginRequestPath,
        String relativePluginRequestPath, String ID, String query,
        HttpServletRequest request, HttpServletResponse response, Locale languageUI) {
      byte[] contingut = null;
      String mime = getMimeType(query);
      query = query.replace('\\', '/');

      query = query.startsWith("/") ? query : ('/' + query);

      try {

        InputStream input = getClass().getResourceAsStream(query);

        if (input != null) {

          contingut = IOUtils.toByteArray(input);

          int pos = query.lastIndexOf('/');
          String resourcename = pos == -1 ? query : query.substring(pos + 1);
          
          OutputStream out = response.getOutputStream();
          

          response.setContentType(mime);
          response.setHeader("Content-Disposition", "inline; filename=\"" + resourcename + "\"");
          response.setContentLength(contingut.length);


          out.write(contingut);
          out.flush();

          return;
        }
      } catch (IOException e) {
        log.error("Error llegint recurs " + query, e);
      }

      // ERROR

      String titol = "No trob el recurs " + query;
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query,
          String.valueOf(ID), request, response, languageUI);
    }

    protected String getMimeType(String resourcename) {
      String mime = "application/octet-stream";
      if (resourcename != null && !"".equals(resourcename)) {
        String type = resourcename.substring(resourcename.lastIndexOf(".") + 1);
        if ("jar".equalsIgnoreCase(type)) {
          mime = "application/java-archive";
        } else if ("gif".equalsIgnoreCase(type)) {
          mime = "image/gif";
        } else if ("cab".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("exe".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("pkg".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("msi".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("js".equalsIgnoreCase(type)) {
          mime = "text/javascript";
        } else if ("zip".equalsIgnoreCase(type)) {
          mime = "application/zip";
        } else if ("css".equalsIgnoreCase(type)) {
          mime = "text/css";
        } else if ("png".equalsIgnoreCase(type)) {
          mime = "image/png";
        }
      }
      return mime;
    }

   
    

    // ---------------------------------------------------------
    // ------------------- DEBUG ------------------------
    // ---------------------------------------------------------

    // TODO XYZ Moure a Plugin Abstracte de tipus WEB
    
    protected void logAllRequestInfo(HttpServletRequest request, String titol,
        String absolutePluginRequestPath, String relativePluginRequestPath, String query,
        String ID) {

      log.info(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, ID));

    }

    protected String allRequestInfoToStr(HttpServletRequest request, String titol,
        String absolutePluginRequestPath, String relativePluginRequestPath, String query,
        String ID) {

      String str1 = pluginRequestInfoToStr(titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, ID);

      String str2 = servletRequestInfoToStr(request);

      return str1 + str2;
    }

    protected String pluginRequestInfoToStr(String titol, String absolutePluginRequestPath,
        String relativePluginRequestPath, String query, String ID) {
      StringBuffer str = new StringBuffer("======== PLUGIN REQUEST " + titol + " ===========\n");
      str.append("absolutePluginRequestPath: " + absolutePluginRequestPath + "\n");
      str.append("relativePluginRequestPath: " + relativePluginRequestPath + "\n");
      str.append("query: " + query + "\n");
      str.append("ID: " + ID + "\n");
      return str.toString();
    }

    protected String servletRequestInfoToStr(HttpServletRequest request) {
      StringBuffer str = new StringBuffer(
          " +++++++++++++++++ SERVLET REQUEST INFO ++++++++++++++++++++++\n");
      str.append(" ++++ Scheme: " + request.getScheme() + "\n");
      str.append(" ++++ ServerName: " + request.getServerName() + "\n");
      str.append(" ++++ ServerPort: " + request.getServerPort() + "\n");
      str.append(" ++++ PathInfo: " + request.getPathInfo() + "\n");
      str.append(" ++++ PathTrans: " + request.getPathTranslated() + "\n");
      str.append(" ++++ ContextPath: " + request.getContextPath() + "\n");
      str.append(" ++++ ServletPath: " + request.getServletPath() + "\n");
      str.append(" ++++ getRequestURI: " + request.getRequestURI() + "\n");
      str.append(" ++++ getRequestURL: " + request.getRequestURL() + "\n");
      str.append(" ++++ getQueryString: " + request.getQueryString() + "\n");
      str.append(" ++++ javax.servlet.forward.request_uri: "
          + (String) request.getAttribute("javax.servlet.forward.request_uri")  + "\n");
      str.append(" ===============================================================");
      return str.toString();
    }

    
    
}