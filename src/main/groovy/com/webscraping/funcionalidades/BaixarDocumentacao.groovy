package com.webscraping.funcionalidades

import com.webscraping.utilitarios.Downloader
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class BaixarDocumentacao {
    static baixarZip() {
        String link

        //1 - String para o acesso a página desejada
        String url = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-2013-setembro-2022'

        //2 - Procedimento para obter o HTML inteiro da página e armazená-lo na variável htmlpage
        Document htmlpage = Jsoup.connect(url).get()

        //3 - Obtendo a tabela através de sua classe "table table-bordered" vide:/imgs/DEMO1.png
        Element tabela = htmlpage.getElementsByClass("table table-bordered").first()

        //4 - Obter o corpo da tabela através de sua tag "tbody" vide:/imgs/DEMO2.png
        Element body = tabela.getElementsByTag("tbody").first()

        //5 - Criar uma lista do tipo Element com todos os elementos com a tag "tr" contidos na tag "tbody", vide:/imgs/DEMO3.png
        List<Element> elementos = body.getElementsByTag("tr")

        //Agora, será feito um loop pela lista elementos, cuja a estrutura é a seguinte:
        /*
        <tr>
        <td>TITULO</td> -> Índice 0
        <td>VERSAO</td> -> Índice 1
        <td>LINK DENTRO DE UMA TAG "a"</td> -> Índice 2
        </tr>
        Portanto, eu quero coletar o que está dentro da tag "a" do índice 2.
        */
        for (Element element : elementos) {
            //Dentro da minha lista, eu vou coletar os elementos presentes com a tag "td"
            List<Element> elementosTD = element.getElementsByTag("td")

            /*Agora, tendo em vista que eu quero o arquivo cujo o título é "Componente de Comunicação e o mesmo pertence ao
            índice 0 da lista construida a cada passagem na lista elementos, faremos a seguinte condicional:
            */
            if(elementosTD.get(0).text()=="Componente de Comunicação")
            {
                //Aonde, será acessado o elemento de índice, acessada a tag "a" e então retirado o link dentro de href
                link = elementosTD.get(2).getElementsByTag("a").first().attr("href")
            }
        }
        String filePath = '/home/rezendecas/Desktop/webscrapingzg/untitled/src/files/download.zip'

        Downloader downloader = new Downloader(link, filePath)
        downloader.downloadfile()
    }
}
