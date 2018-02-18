package com.home.work.wheatherapp
import com.google.common.reflect.ClassPath
import spock.lang.Specification

class ReadFileSpec extends Specification{


    def readfile_with_FileReader(){

        given:"weather lat long input file"
        String baseuri='src/test/resources'
        File file =new File((baseuri+'/weatherdata/worldcitiespop.txt'))

        when:"read file"
        def lineNo = 1
        def line
        file.withReader { reader ->
            while ((line = reader.readLine())!=null) {
                println "${lineNo}. ${line}"
                String[] eachline = line.split()
                print(eachline)
                lineNo++
            }
        }

        then:"print result"
        println('success')
    }



    def read_file_with_file_reader(){

        given:"weather lat long input file"
        String baseuri='src/test/resources'
        File file =new File((baseuri+'/weatherdata/worldcitiespop.txt'))
        File file1 =new File(ClassPath.getResource('/weatherdata/worldcitiespop.txt').toURI())
        when:"read file"
        def lineNo = 1
        def line
        long starTime=System.currentTimeMillis()
        file.withReader { reader ->
            while ((line = reader.readLine())!=null) {
                println "${lineNo}. ${line}"
                sendData(line)
                lineNo++
            }
        }
        long stopTime=System.currentTimeMillis()

        print("total time taken in milisecond " + (stopTime-starTime))
        then:"print result"
        println('success')
    }


    def read_file_with_file_reader_uri(){

        given:"weather lat long input file"
        String baseuri='src/test/resources'
        //File file =new File((baseuri+'/weatherdata/worldcitiespop.txt'))
        File file =new File(ClassPath.getResource('/weatherdata/sample.txt').toURI())
        when:"read file"
        def lineNo = 1
        def line
        long starTime=System.currentTimeMillis()
        file.withReader { reader ->
            while ((line = reader.readLine())!=null) {
                println "${lineNo}. ${line}"
                sendData(line as String)
                lineNo++
            }
        }
        long stopTime=System.currentTimeMillis()

        print("total time taken in milisecond " + (stopTime-starTime))
        then:"print result"
        println('success')
    }

    def read_file_with_file_classpath_Resource(){

        given:"weather lat long input file"
        String baseuri='src/test/resources'


        when:"read file"
        def lineNo = 1
        long starTime=System.currentTimeMillis()
        ClassPath.getResource('/weatherdata/worldcitiespop.txt').getText('utf-8').eachLine {
            println "${lineNo}. ${it}"
            sendData(it)
            lineNo++
        }

        long stopTime=System.currentTimeMillis()
        print("total time taken in milisecond " + (stopTime-starTime))
        then:"print result"
        println('success')



    }



    def read_file_with_file_readlines(){

        given:"weather lat long input file"
        String baseuri='src/test/resources'


        when:"read file"
        def lineNo = 1
        long starTime=System.currentTimeMillis()
        File file =new File((baseuri+'/weatherdata/worldcitiespop.txt'))
        List<String> fulltext=file.readLines()
        fulltext.each {
            println "${lineNo}. ${it}"
            sendData(it)
            lineNo++
        }
        long stopTime=System.currentTimeMillis()
        print("total time taken in milisecond " + (stopTime-starTime))
        then:"print result"
        println('success')

    }


    void sendData(String line){

        String[] eachLine=line.split(',')
        println("eachline : country : ${eachLine[0]} city :${eachLine[1]} accentCity :${eachLine[2]} region :${eachLine[3]} population :${eachLine[4]} lat:${eachLine[5]} lon:${eachLine[6]}")
    }
}
