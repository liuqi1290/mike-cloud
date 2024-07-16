package com.mike.server.system.controller;

import com.mike.server.system.service.PdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@Api(tags = "【PDF-管理】")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class PdfController {

    private final PdfService pdfService;

    @PostMapping(value = "/generate-pdf")
    @ApiOperation(value = "生成PDF", produces = "application/octet-stream")
    public void generatePdf(@RequestBody Map<String, String> params) {
        pdfService.generatePdf(params);
    }
}
