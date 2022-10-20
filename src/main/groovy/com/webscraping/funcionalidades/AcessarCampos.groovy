package com.webscraping.funcionalidades

import com.webscraping.classes.Componentes
import groovy.json.JsonBuilder
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class AcessarCampos {
    static acessar(){
        //1 - Link a ser acessado pelo sistema
        String url = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss'

        //2 - Obtendo uma cópia do HTML inteiro da página
        Document htmlpage = Jsoup.connect(url).get()

        //3 - Coletar o elemento que contém a tabela
        Element divTabela = htmlpage.getElementById("parent-fieldname-text")

        //4 - Coletar a tabela através da sua tag - Vide: DEMO4
        Element tabela = divTabela.getElementsByTag("table").first()

        //5 - Coletar todos os elementos presentes na tabela - Vide: DEMO5
        Element elementos = tabela.getElementsByTag("tbody").first()
        /*
        Que irá me retornar o seguinte
         <tr>
          <td></td>,
          ...
          <td></td>
         </tr>
        */

        //6 - Armazenar esses elementos em uma list de Elements
        List<Element> componentesraw = elementos.getElementsByTag("tr")

        //7 - Montar uma lista vazia de com.webscraping.classes.Componentes
        List<Componentes> lista_componentes = new ArrayList<>()

        //8 - Passar por cada componente na lista componentes e adicionar na lista_componentes
        for(Element componente: componentesraw)
        {
            List<Element> tds = componente.getElementsByTag("td")
            //Onde essa lista será constituida por
            //[<td>Competência</td>,
            // <td>Publicação</td>,
            // <td>Início de vigência</td>,
            // <td>Limite de Implementação</td>,
            // <td>Organizacional</td>,
            // <td>Conteúdo e estrutura</td>,
            // <td>Representação de Conceitos</td>,
            // <td>Segurança e Privacidade</td>,
            // <td>Comunicação</td>]

            //Tendo em vista que nos interessa, o itens cujo os indexadores são: 0, 1 e 2.
            //Instanciamos um objeto da classe Compenentes
            //MAS antes de instanciarmos, devemos verificar se a competência é de jan/2016, portanto:
            if(tds.get(0).text()=="jan/2016")
            {
                Componentes componentes = new Componentes(tds.get(0).text(),tds.get(1).text(),tds.get(2).text())
                lista_componentes.add(componentes)
                break
            }
            else {
                Componentes componentes = new Componentes(tds.get(0).text(), tds.get(1).text(), tds.get(2).text())
                lista_componentes.add(componentes)
            }
        }
        //Agora, com uma lista com os componentes desejados, iremos escreve-los em um arquivo
        File file = new File('/home/rezendecas/Desktop/webscrapingzg/untitled/src/files/data.json')
        String jsonDATA = '['
        for(Componentes componente : lista_componentes)
        {
            String json = new JsonBuilder(componente)
            jsonDATA +=json+','
        }

        //Método para remover a última vírgula do Json
        jsonDATA = jsonDATA.substring(0,jsonDATA.length()-1)
        jsonDATA +=']'

        file.write(jsonDATA)
    }
}
