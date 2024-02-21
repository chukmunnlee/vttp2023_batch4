package vttp.batch4.csf.day38.upload.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.batch4.csf.day38.upload.models.ImageData;
import vttp.batch4.csf.day38.upload.respositories.ImageRepository;


@Controller
@RequestMapping
public class PictureController {

   @Autowired
   private ImageRepository imageRepo;

   @GetMapping("/picture/{picId}")
   @ResponseBody
   public ResponseEntity<byte[]> getPicture(@PathVariable String picId) {

      Optional<ImageData> opt = imageRepo.getPicture(picId);
      ImageData data = opt.get();

       return ResponseEntity.status(200)
            .header("Content-Type", data.mediaType())
            .header("Cache-Control", "max-age=604800")
            .body(data.contents());
   }
   
   
}
