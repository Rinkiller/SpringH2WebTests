package Rin.compani.H2DBUser.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


@Aspect
@Component
@Order(3)
public class CastomAspectByTrackUserAction {


    @Around("@annotation(TrackUserAction)")
    public void logBeforeMethodCall(JoinPoint joinPoint)  {
        System.out.println("------CastomAspectByTrackUserAction-------");
        String nameOfMetod = joinPoint.getSignature().getName();
        File f = new File("myFileLogOfTrackUser.txt");
        if(!(f.exists() && !f.isDirectory())) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String str = "Metod " + joinPoint.getSignature().getName() +" was called "+ java.time.LocalDateTime.now ().toString(); ;
        byte[] buffer = str.getBytes();
        try {
            outputStream.write(buffer);
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
