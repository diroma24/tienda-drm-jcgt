package es.iesclaradelrey.da2d1a.tiendadrmjcgt.common.services;

import jakarta.servlet.http.HttpServletResponse;

public interface ExportacionService {
    void exportarProductosXml(HttpServletResponse response);
}