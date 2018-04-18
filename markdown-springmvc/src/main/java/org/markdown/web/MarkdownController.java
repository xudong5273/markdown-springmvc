package org.markdown.web;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.markdown.entity.MarkDown2HtmlWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Controller
public class MarkdownController {

    /**
     * 通过/md/**.md 获取文件
     * @param path 文件名**.md
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/md/{*.md}")
    public String markdowntoweb(@PathVariable("*.md")String path,Model model) throws IOException {
        String html = MarkDown2HtmlWrapper.ofFile(path).toString();
        model.addAttribute("html",html);
        //md.jsp
        return "md";
    }

    /**
     * 通过绝对路径获取文件
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/path")
    public String markdowntoweb2(Model model) throws IOException {
        String path="F:/q.md";
        String html = MarkDown2HtmlWrapper.ofFile(path).toString();
        model.addAttribute("html",html);
        return "md";
    }

    /**
     * 网络地址获取md文件
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/webpath")
    public String markdowntoweb3(Model model) throws IOException {
        URL url = new URL("http://122.112.230.55/api/API.md");
        InputStream inputStream=url.openStream();
//        byte[] bytes=new byte[1024*5];
//        try {
//            int len=0;
//            while((len=inputStream.read(bytes))!=-1) {
//                inputStream.read(bytes,0,len);
//            }
//        }catch (IOException e){
//                e.printStackTrace();
//        }finally {
//            if(inputStream!= null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        //String str=new String(bytes,"utf-8");
        String html = MarkDown2HtmlWrapper.ofStream(inputStream).toString();
        System.out.println(html);
        model.addAttribute("html",html);
        return "md";
    }
}
