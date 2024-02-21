package vttp.batch4.csf.day38.upload.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import vttp.batch4.csf.day38.upload.services.ImageService;


@Controller
@RequestMapping
public class UploadController {

   @Autowired
   private ImageService imageSvc;

   @PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ModelAndView postMethodName(@RequestPart MultipartFile myfile
         , @RequestPart String comments) {

      ModelAndView mav = new ModelAndView("mypicture");

      System.out.printf(">>>> name: %s\n", myfile.getName());
      System.out.printf(">>>> orignal file name: %s\n", myfile.getOriginalFilename());
      System.out.printf(">>>> size: %d\n", myfile.getSize());
      System.out.printf(">>>> content type: %s\n", myfile.getContentType());
      System.out.printf(">>>> comments: %s\n", comments);

      try {
         //String picId = imageSvc.save(myfile.getInputStream(), myfile.getContentType());
         //mav.addObject("imageUrl", "/picture/%s".formatted(picId));
         String picId = imageSvc.saveToS3(myfile.getInputStream(), myfile.getContentType()
               , myfile.getSize());
         mav.addObject("imageUrl", 
            "https://vttp-batch4.sgp1.digitaloceanspaces.com/images/%s".formatted(picId));
      } catch (IOException ex) {
         ex.printStackTrace();
         mav.addObject("imageUrl", "/joker.png");
      }

      mav.addObject("comments", comments);

      return mav;
   }
}
