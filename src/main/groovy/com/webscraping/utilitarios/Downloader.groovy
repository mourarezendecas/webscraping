package com.webscraping.utilitarios

import groovyx.net.http.HttpBuilder
import groovyx.net.http.optional.Download

class Downloader extends Exception{
    String url, filePath

    Downloader(String url, String filePath) {
        this.url = url
        this.filePath = filePath
    }

    def downloadfile()
    {
        File file = HttpBuilder.configure{
            request.uri = url
        }.get{
            Download.toFile(delegate, new File(filePath))
        } as File
        file.createNewFile()
    }
}
