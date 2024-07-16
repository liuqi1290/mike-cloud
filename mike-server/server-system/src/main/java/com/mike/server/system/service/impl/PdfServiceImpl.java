package com.mike.server.system.service.impl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.mike.common.core.utils.ServletUtils;
import com.mike.server.system.service.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PdfServiceImpl implements PdfService {

    private final HttpServletResponse response;

    @Override
    @SneakyThrows
    public void generatePdf(Map<String, String> params) {


        // 读取资源文件夹下的模板
        ClassPathResource resource = new ClassPathResource("pdf-template/简单劳动合同模板.pdf");
        InputStream inputStream = resource.getInputStream();

        /*
         * 或者通过 url 从网上下载 pdf 模板文件
         *
            // 获取文件地址
            String urlPath = "模板资源文件链接-url";
            // 下载文件
            URL url = new URL(urlPath);
            URLConnection connection = url.openConnection();
            // 设置请求超时时长为 5 秒
            connection.setConnectTimeout(5*1000);
            // 读取数据
            InputStream inputStream = connection.getInputStream();
         */

        PdfReader reader = null;
        ByteArrayOutputStream bos = null;
        try {
            reader = new PdfReader(inputStream);
            bos = new ByteArrayOutputStream();
            PdfStamper pdfStamper = new PdfStamper(reader, bos);
            AcroFields acroFields = pdfStamper.getAcroFields();

            // 中文字体
            BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            for (Map.Entry<String, String> param : params.entrySet()) {
                // 设置文本域的字体为中文字体
                acroFields.setFieldProperty(param.getKey(), "textfont", font,null);
                // 将 map 中的值写到 pdf 模板对应的文本域中
                acroFields.setField(param.getKey(), param.getValue());
            }

            // 如果为false那么生成的PDF文件还能编辑，所以一定要设为true
            pdfStamper.setFormFlattening(true);
            pdfStamper.close();
            // 返回文件
            ServletUtils.writeAttachment(response, "劳动合同.pdf", bos.toByteArray());
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bos != null;
                bos.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
