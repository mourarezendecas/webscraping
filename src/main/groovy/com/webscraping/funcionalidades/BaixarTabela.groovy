package com.webscraping.funcionalidades

import com.webscraping.utilitarios.Downloader
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class BaixarTabela {
    static baixarPlanilha(){
        //1 - Encontrar a URL desejada
        String url = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-tabelas-relacionadas'

        //2 - Extrair o HTML e inserir em uma variavel
        Element htmlPage = Jsoup.connect(url).get()

        //3 - Extrair a div que contém as tabelas - Vide Demo 6
        Element divTabelas = htmlPage.getElementById('parent-fieldname-text')

        //4 - Como desejamos baixar a primeira tabela, filtratemos divTabelas pela tag "a"
        Element element = divTabelas.getElementsByTag("a").first()
        String link = element.attr("href")
        String filePath = '/home/rezendecas/Desktop/webscrapingzg/untitled/src/files/padrao.xlsx'

        Downloader downloader = new Downloader(link, filePath)
        downloader.downloadfile()
    }

}
