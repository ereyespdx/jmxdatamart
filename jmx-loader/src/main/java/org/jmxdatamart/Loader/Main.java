package org.jmxdatamart.Loader;/*
 * Copyright (c) 2013, Tripwire, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  o Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.jmxdatamart.common.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.File;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(Main.class);
        if (args.length!=2){
            System.err.println("Must have two arguments.\nUsage: loader settingFile folderLocation");
            //System.exit(1);
        }

//        String arg0 = "jmx-loader/src/main/java/org/jmxdatamart/Loader/loaderconfig.ini";
//        String arg1 = "HyperSql/";
        String arg0 = args[0];
        String arg1 = args[1];

        File prop = new File(arg0);
        if (!prop.isFile()){
            System.err.println("Invalid file.");
            System.exit(1);
        }
        File folder = new File(arg1) ;
        if (!folder.isDirectory()){
            System.err.println("Invalid folder.");
            System.exit(1);
        }

        Setting s = new Setting(arg0);
        DB2DB d2d = new DB2DB(s,folder);
        try{
            logger.info("\nLoadding data from " + arg1 + ".\n");
            d2d.importData();
            logger.info("\nData are successfully imported to DataMart from " + arg1);
        }
        catch (SQLException se){
            logger.error("\nFail to import data from " + arg1 + ": \n" +se.getMessage());
        }
        catch (DBException de){
            logger.error("\nFail to import data from " + arg1);
        }
    }


}


