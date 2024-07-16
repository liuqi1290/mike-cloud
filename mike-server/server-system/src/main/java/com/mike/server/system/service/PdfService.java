package com.mike.server.system.service;

import java.util.Map;

public interface PdfService {

    void generatePdf(Map<String, String> params);
}
