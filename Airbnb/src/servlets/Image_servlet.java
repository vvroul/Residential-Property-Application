package servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Image_servlet
 */
@WebServlet("/Image_servlet/*")
public class Image_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Properties ---------------------------------------------------------------------------------

    private String imagePath = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent";
    
    // private String imagePath = "Users/Geo/workspace/Airbnb/WebContent/user_images"

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get requested image by path info.
        String requestedImage = request.getPathInfo();
        
        //System.err.println("i am inside");
        
        //System.err.println("requestedImage = " + requestedImage);

        // Check if file name is actually supplied to the request URI.
        if (requestedImage == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            System.err.println("url not ok");
            return;
        }

        // Decode the file name (might contain spaces and on) and prepare file object.
        File image = new File(imagePath , URLDecoder.decode(requestedImage, "UTF-8"));
        
        //System.err.println("image.getName() = " + image.getName());
        //System.err.println("image.getPath() = " + image.getPath());

        // Check if file actually exists in filesystem.
        if (!image.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            System.err.println("image not exists");
            return;
        }
        
        //System.err.println("image exists");

        // Get content type by filename.
        ServletContext context = getServletContext();
        String contentType = context.getMimeType(image.getName());
        
        //System.err.println("contentType = " + contentType);

        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            System.err.println("not an image");
            return;
        }

        // Init servlet response.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Write image content to response.
        Files.copy(image.toPath(), response.getOutputStream());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
