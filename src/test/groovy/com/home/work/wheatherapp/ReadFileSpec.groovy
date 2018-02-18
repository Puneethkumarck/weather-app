package com.home.work.wheatherapp

import spock.lang.Specification

class ReadFileSpec extends Specification{


    def readfile(){

        given:"weather lat long input file"
        String baseuri='src/test/resources'
        File file =new File((baseuri+'/weatherdata/worldcitiespop.txt'))

        when:"read file"
        def lineNo = 1
        def line
        file.withReader { reader ->
            while ((line = reader.readLine())!=null) {
                println "${lineNo}. ${line}"
                lineNo++
            }
        }

        then:"print result"
        println('success')
    }
}
