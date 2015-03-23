/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 */
public final class Main {

    public static final String GOOGLE_QUERY_BASE_URL = "https://www.google.com/search";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)";
    public static int min_number_of_wait_times = 6;
    public static int max_number_of_wait_times = 15;
    public static final String PARAM_CS_QUERY = "?q=";
    public static final String LOG_PREFIX = "[Miner WebFooter]";
    public static final int PARAM_CS_TIMEOUTMS = 0;
    public static final String PARAM_CS_QUERY_PAGE = "&start=";


    public static void main(String[] args){


        String[] emails = new String[]{
                "fullspectrumsoftware@leadgnome.com",
                "mnizzari@patientslikeme.com",
                "dmarttila@patientslikeme.com",
                "Rebecca.Rienks@eonline.com",
                "Mobeen.Khan@att.com",
                "Mike.Troiano@att.com",
                "Dustin.Heslep@gapac.com",
                "Marilyn.Mann@gapac.com",
                "Davidb@clear-ideas.com",
                "jmyers@emausa.com",
                "craig.mcgettigan@bms.com",
                "barbara.salami@bms.com",
                "qiaoli1@lenovo.com",
                "harmog00@dcor.state.ga.us",
                "JessieWanChu.Lim@team.telstra.com",
                "James.Hosang@directlinegroup.co.uk",
                "James.Hosang@directlinegroup.co.uk",
                "dcconnell@deloitte.co.uk",
                "it.security.uk@deloitte.co.uk",
                "ray.s@appnomic.com",
                "kerry.h@appnomic.com",
                "Drew.Clarke@qlik.com",
                "linda_mcmillan@baxter.com",
                "Dinsmore.Thomas@bcg.com",
                "BThurstin@us.imshealth.com",
                "Dipanjan.Das@capitalone.com",
                "dguericke@appen.com",
                "Sanna-Katriina.Asikainen@lut.fi",
                "mkumaran@marsdd.com",
                "psargent@newsbank.com",
                "rene.alfonso@citrix.com",
                "todd.carter@sccc.edu",
                "bshrinev@cisco.com",
                "John_Ruberto@intuit.com",
                "minarik@amazon.com",
                "Niels.Lohse@nottingham.ac.uk",
                "n.lohse@lboro.ac.uk",
                "terrence.poon@jasper.com",
                "amit.gupta@jasper.com",
                "sreed@adsinc.com",
                "areints@digitalriver.com",
                "elanser@digitalriver.com",
                "kaizhen.li@thermofisher.com",
                "jean-marc.bohlen@thermofisher.com",
                "deborah.dantonel@thermofisher.com",
                "piorg@splunk.com",
                "ssorkin@splunk.com",
                "kbergmann@splunk.com",
                "don.brooks@inovapartners.com",
                "Sean.Webb@drdc-rddc.gc.ca",
                "kapoor.a.3@pg.com",
                "Jennifer.Biggs@BeamSuntory.com",
                "trenfro@ubalt.edu",
                "debra.braun@hp.com",
                "sonja.bates@shopdirect.com",
                "DanielJasper.Puga@wns.com",
                "Hillary.Delabar@tig.com",
                "Ronald.Pegram@grainger.com",
                "ronald.pegram@grainger.com",
                "BTordell@its.jnj.com",
                "erector@cisco.com",
                "alwalews@cisco.com",
                "mutter@cisco.com",
                "JessicaW@payscale.com",
                "DaveSm@payscale.com",
                "Kate.AlMosawi@ShopDirect.com",
                "Katherine.Fenerty@teachforamerica.org",
                "katie.katie@teachforamerica.org",
                "emilio.mata@accenture.com",
                "Sarah_Kohler@trilliumsoftware.com",
                "bhkrishn@cisco.com",
                "ernesto.miyakawa@boavistaservicos.com.br",
                "Benjamin.Hill@moodys.com",
                "tracey.ferguson@moodys.com",
                "xiang.zhu2@hp.com",
                "Aditya.Nadendla@in.unisys.com",
                "mike.gonzalez@verizon.com",
                "verizoncentralinsights@verizon.com",
                "dlewis@agile1.com",
                "marie-stephane.doize@capgemini.com",
                "fr-mpc@schneider-electric.com",
                "mdelaossa1@bloomberg.net",
                "elin.ramsey@docusign.com",
                "Sean.Slack@Teradata.com",
                "gjohnson@lciinc.com",
                "mquiogue@lciinc.com",
                "jewell.106@osu.edu",
                "vibhu.prakash@capgemini.com",
                "Jarrod.Olson@pnnl.gov",
                "trish.nygreen@pnnl.gov",
                "jpbrown4@liberty.edu",
                "David.Qing@gfk.com",
                "Gary.Seaman@shgroup.org.uk",
                "christoph.bartneck@canterbury.ac.nz",
                "ken.beckman@canterbury.ac.nz",
                "chang.d.1@pg.com",
                "Cris_Lubbat@Dell.com",
                "Kyle_Trusty@DELL.com",
                "Sam_Dixon@Dell.com",
                "Phillip_Dulka@Dell.com",
                "Richie_Pittenger@Dell.com",
                "Ami_Patel@Dell.com",
                "rakesh.balani@BIRLASOFT.COM",
                "mail.admin@birlasoft.com",
                "phillip.morris@bt.com",
                "oliver@amobee.com",
                "cheng.d.3@pg.com",
                "Amritha.Shetty@igate.com",
                "Sowmya_H@Dell.com",
                "Harshada.Tamhankar@Sciformix.com",
                "siew-weng.kuan@hp.com",
                "fang.y.1@pg.com",
                "Hijam.Singh@mu-sigma.com",
                "arvind.r@mu-sigma.com",
                "Ramesh.Anantarapu@Honeywell.com",
                "lincoln.wood@aut.ac.nz",
                "ludwina.lafaele@aut.ac.nz",
                "Keegan.OShea@optus.com.au",
                "ray_crowley@trendmicro.ie",
                "arkadev.basak@everestgrp.com",
                "rajesh.ranjan@everestgrp.com",
                "gaalen@schiphol.nl",
                "h.wang@ecit.qub.ac.uk",
                "dario.ravarro@digitalbarriers.com",
                "jacqueline.chua@digitalbarriers.com",
                "jackson.teo@digitalbarriers.com",
                "v.deleonibus@inventya.com",
                "c.meredew@inventya.com",
                "k.morris@inventya.com",
                "Baumanna@zv.tum.de",
                "Maike.Loehndorf@kapsch.net",
                "paul.bouchier@ifsworld.com",
                "bernhard.anrig@bfh.ch",
                "Casper.Moeller.Frederiksen@regionh.dk",
                "erik.viuff.hansen@regionh.dk",
                "Mark.Price@monitise.com",
                "greg.hunt@monitise.com",
                "peter.allcock@monitise.com",
                "Phred.Groves@whitespacews.com",
                "mnaufal@hp.com",
                "dmeyer@swin.edu.au",
                "ccritchley@swin.edu.au",
                "patrick_chen@trend.com.tw",
                "asshahrani@nic.gov.sa",
                "mlwaimy@nic.gov.sa",
                "Daniel.Smaragd@ucb.com",
                "Benjamin.Matten@nttdata.com",
                "lutum@geomarketing.de",
                "Twan.Bearda@imec.be",
                "Christo.Kieser@aramex.com",
                "Lucy.Stan@hif.com.au",
                "anne.humphrey@hif.com.au",
                "fahim.mukhtar@webapplicationsuk.com",
                "Annelies.Paulus@sirris.be",
                "lionel.frachon@thalesgroup.com",
                "JMealy@geneva-trading.com",
                "cels@geneva-trading.com",
                "detlev.goebel@firstdata.de",
                "menhancements@admr.com",
                "LKeller@eab.com",
                "akrenkel@eab.com",
                "sgr@neasenergy.com",
                "ito@neasenergy.com",
                "Benno.Geisselmann@bosch-si.com",
                "Michael.Barker@frw.co.uk",
                "barkermj@yahoo.co.uk",
                "bschulte@digitalriver.com",
                "rniegoth@digitalriver.com",
                "veli-pekka.julkunen@marketingclinicpartners.com",
                "German.Otarola@forestal.cmpc.cl",
                "major.enhancements@forestal.cmpc.cl",
                "DReynaert@techdata.de",
                "Pascal.Baquet@mil.be",
                "eric.leflour@predicsis.com",
                "laurence.feuillas@predicsis.com",
                "c.ossiander@statarts.de",
                "d.amoneit@statarts.de",
                "gerrit.fiol@hhi.fraunhofer.de",
                "Nikolaos.Koutsouleris@med.uni",
                "Ursula.Stangel@gabo-mi.com",
                "Hanspeter.Helfer@bonuscard.ch",
                "customer.analytics@bonuscard.ch",
                "Rudolf.Reinhard@ima-zlw-ifu.rwth",
                "max.hoffmann@ima.rwth-aachen.de",
                "Anthony.Birchall@res-ltd.com",
                "rosa.gindele@res-ltd.com",
                "jamie.scurlock@res-ltd.com",
                "Stuart.Robb@marketingsource.co.uk",
                "CsVigh@TELENOR.HU",
                "Simeon.Coney@adaptivemobile.com",
                "securitypractice@adaptivemobile.com",
                "G.Meester@ind.minvenj.nl",
                "gerhard.v.kress@siemens.com",
                "aurelien.henry@cgi.com",
                "thomas.muehlbacher@landwirt.com",
                "office@egon.cx",
                "t.woehrer@sih.co.at",
                "Richard.lewis@imgroup.com",
                "MPearce2@rac.co.uk",
                "Andrew.Reilly@clatterbridgecc.nhs.uk",
                "maja.salamon@pbz.hr",
                "Anne-Marie.Scott@ed.ac.uk",
                "Andreas.Scholl@viega.de",
                "Johannes.Mockenhaupt@h-brs.de",
                "Werner.Kruger@standardbank.co.za",
                "Neil.Evans@occam-dm.com",
                "JPeenz@fnb.co.za",
                "nhlapos@fnb.co.za",
                "ovunc.ozbilgic@cignafinans.com.tr",
                "merve.simsek@cignafinans.com.tr",
                "Soude.Fazeli@ou.nl",
                "Raghavendra.Bhat@mwhglobal.com",
                "simonetta.colaianni@accenture.com",
                "christoph.schult@damanhealth.ae",
                "gina.secapore@damanhealth.ae",
                "mohammed.jassim@damanhealth.ae",
                "",
                "Laura.Uusitalo@ymparisto.fi",
                "H-Lehmann@telekom.de",
                "frans.nijenhuis@philips.com",
                "Eoin.Kearney@airtricity.com",
                "niamh.delaney@airtricity.com",
                "Holger.Kett@iao.fraunhofer.de",
                "J_Pimentel@networkresearch.co.uk",
                "ieva.leja@fontes.lv",
                "inese.zepa@fontes.lv",
                "Michael.Keane@itb.ie",
                "arpaniagua@gmv.com",
                "rgalan@gmv.com",
                "rainer.wasgint@siemens.com",
                "m.monti@casale.ch",
                "Adam.Asprey@occam-dm.com",
                "paul.smith@occam-dm.com",
                "fernanda.dorea@sva.se",
                "ess-extern@sva.se",
                "gordon.salzmann@evaco.de",
                "kristina.hanisch@evaco.de",
                "martine.falhon@temis.com",
                "daniel.mayer@temis.com",
                "Teemu.Lehto@qpr.com",
                "customercare@qpr.com",
                "tom.kuehner@capgemini.com",
                "michael.schulte@capgemini.com",
                "peter.lempp@capgemini.com",
                "anneleen@ascensionexecutivesearch.com",
                "nigel.stocks@jatomi.com",
                "bartlomiej.kulasza@jatomi.com",
                "katerina.vrotsou@liu.se",
                "Shengguang.Lei@eu.Takata.com",
                "Valentina.Djokic@eu.Takata.com",
                "Tarek.Fouad@pta.de",
                "klaus.basters@pta.de",
                "nils.kristensen@no.ey.com",
                "sebnem.rusitschka@siemens.com",
                "Robert.Don-Duncan@ao.com",
                "businessintelligence@ao.com",
                "AAGBEKO@ecobank.com",
                "fdapelgo@ecobank.com",
                "philippe.a.andre@sogeti.com",
                "frank.marmann@capgemini.com",
                "Mark.Carlock@Clarks.com",
                "armin.lang@ntsretail.com",
                "giuseppe.bee@agroscope.admin.ch",
                "louis.smith@airtricity.com",
                "risto.tiainen@nsn.com",
                "Jack.McGuire@UNIBET.com",
                "marcus.furuholmen@akersolutions.com",
                "marc.balston@db.com",
                "richardr@hubbard.co.uk",
                "amacle@slb.com",
                "joachim.kohlhofer@siemens.com",
                "Graeme.McDermott@TheAA.com",
                "Helen.Broughton@sabmiller.com",
                "Adrian.Mandipe@quins.co.uk",
                "ola.obaro@quins.co.uk",
                "guy.vandersande@ext.usgict.be",
                "saugust@focusvision.com",
                "xinhwang@deloitte.ch",
                "Bart.Roelands@be.atlascopco.com",
                "Rob.Mosley@staffordshirecss.nhs.uk",
                "Damien.Convert@automic.com",
                "eric.monteux@alyotech.fr",
                "Torsten.Preissler@dentsply.com",
                "shaun.dunmall@nhs.net",
                "tom.e.lewis@uk.pwc.com",
                "brooke.gilmore@uk.pwc.com",
                "Valerie.Pattyn@soc.kuleuven.be",
                "Anita.Regucka-Kwasnik@version1.com",
                "Jukka.Pappinen@finnhems.fi",
                "Markus.Ferstl@defacto-x.de",
                "chandra.patel@c9inc.com",
                "Stephen.Fleming@yrgrp.com",
                "global.intelligence@yrgrp.com",
                "RYAN.MCGOWAN@adtran.com",
                "Jonathan.Lin@encana.com",
                "John.Poppelaars@ortec.com",
                "els.koster@ortec.com",
                "Gary.Connolly@acenden.com",
                "darryl.hasieber@acenden.com",
                "henri.roponen@wartsila.com",
                "vincent.ahrens@davidoff.ch",
                "a.moss@chesterzoo.org",
                "lewis.linn-cole@xpisimulation.com",
                "gclemmer@newurbanresearch.com",
                "Cristiano_Grassi@carraro.com",
                "Jarire.West@inova.org",
                "Mohammad.Iqbal-Simon@arvato.com",
                "jshaffer@michigan.com",
                "mozdych@michigan.com",
                "sbelcher@michigan.com",
                "aaithamd@uw.edu",
                "",
                "WEngelbrecht@pcrm.org",
                "CClyne@pcrm.org",
                "browland@okstate-uml.org",
                "jdollan@okstate-uml.org",
                "kking@nsf.gov",
                "dhaury@nsf.gov",
                "Dia.Trambitas@recognos.ro",
                "robert.baban@recognos.ro",
                "qijie.cai@mnsu.edu",
                "WeaverC1@michigan.gov",
                "",
                "eu-nice.lim@accenture.com",
                "belinda.white@accenture.com",
                "Vitalii.Doban@pfizer.com",
                "jagmohan.sehgal@accenture.com",
                "rohit.dutta@accenture.com",
                "margot@vanwardstat.com",
                "mleece@brocku.ca",
                "rima.dasgupta@capgemini.com",
                "amcdonald@admr.com",
                "Benzigar.Patrick@igate.com",
                "kitty@digitalalchemy.com.au",
                "Charl.Swart@au.unisys.com",
                "ziyi.long@novartis.com",
                "Fergal.Quinn@salmat.com.au",
                "Nick.Spooner@salmat.com.au",
                "VIBHORKA@amdocs.com",
                "ritesh.shah@amdocs.com",
                "praveen.p@blueoceanmi.com",
                "",
                "aparna.mishra1@in.ey.com",
                "jbarlow@capstrat.com",
                "ksutton@capstrat.com",
                "tcoats@capstrat.com",
                "mjones@capstrat.com",
                "rhutchins@advantagecap.com",
                "charris@advantagecap.com",
                "Allan.Pyl@mpac.ca",
                "david.zheng@mpac.ca",
                "david.schaefer@mheducation.com",
                "colleen.kerwan@mheducation.com",
                "melonie.salvati@mheducation.com",
                "Richard.Quick@niceactimize.com",
                "megan.heald@niceactimize.com",
                "martin.anstis@niceactimize.com",
                "li.zhou@niceactimize.com",
                "pierre.valk@tno.nl",
                "willeke.roodenburg@tno.nl",
                "Jessica.Ekholm@gartner.com",
                "euro.inquiry@gartner.com",
                "pawel.manowiecki@qlik.com",
                "sandip.ray@intel.com",
                "jin.yang@intel.com",
                "keith.brady@au.cyberscience.com",
                "debabrata.midya@finance.nsw.gov.au",
                "bestpractice@finance.nsw.gov.au",
                "Paul.Oosterveld@tns-nipo.com"
        };

        //parseCompany("http://dnssab.on.ca");
        //parseCompany("http://leadgnome.com");
        //parseCompany("http://carnegielearning.com");
        //parseCompany("http://enterprisemanagement.com");
        //parseCompany("http://emausa.com");

        for(String email : emails)if(!email.equals("")){
            parseCompany("http://"+email.split("@")[1]);
        }

    }

    public static void parseCompany(String searchEndPoint) {
        //String searchEndPoint= "http://ibm.com";
        Document HTMLdoc;
        try {
            HTMLdoc = Jsoup.connect(searchEndPoint)
                    .ignoreHttpErrors(true)
                    .userAgent(USER_AGENT)
                    .ignoreHttpErrors(true)
                    .timeout(PARAM_CS_TIMEOUTMS)
                    .get();
            Date d0=new Date();

            Elements select = HTMLdoc.select("*:matchesOwn(Copyright|\\xA9|\\(C\\)|Copyright of)");

            System.out.println("----------------------------------------------");
            System.out.println(searchEndPoint);
            System.out.println("----------------------------------------------");

            Date d1=new Date();


            Pattern shortRegEx1 = Pattern.compile("([a-z \\&@!\\xAE\\x99,\\.\\/]+)[ ,]*(?:\\d{4,4}(?:[ ]*[-,][ ]*\\d{4,4})?)[ ]*(?:Copyright|\\xA9|\\(C\\))", Pattern.CASE_INSENSITIVE );
            Pattern shortRegEx2 = Pattern.compile("(?:\\d{4,4}(?:[ ]*[-,][ ]*\\d{4,4})?)[ ,]*(?:Copyright|\\xA9|\\(C\\))[ ]*([a-z \\&@!\\xAE\\x99,\\.\\/]+)", Pattern.CASE_INSENSITIVE );
            Pattern shortRegEx3 = Pattern.compile("(?:Copyright|\\xA9|\\(C\\))[ ]*(?:\\d{4,4}(?:[ ]*[-,][ ]*\\d{4,4})?)[ ,]*([a-z \\&@!\\xAE\\x99,\\.\\/]+)", Pattern.CASE_INSENSITIVE );
            Pattern shortRegEx4 = Pattern.compile("([a-z \\&@!\\xAE\\x99,\\.\\/]+)[ ,]*(?:Copyright|\\xA9|\\(C\\))[ ]*(?:\\d{4,4}(?:[ ]*[-,][ ]*\\d{4,4})?)", Pattern.CASE_INSENSITIVE );
            Pattern removeExtra = Pattern.compile("\\.[ ]*(All rights|See Terms).*", Pattern.CASE_INSENSITIVE );

            Matcher m;
            String company=null;
            forElements:
            for( Element s : select ){
                String text = s.text();

                //Company 2015 (C)
                m = shortRegEx1.matcher(text);
                System.out.println("Company 2015 (C) ?");
                while( m.find() ) {
                    System.out.println( "gruop[0]=" + m.group(0) );
                    System.out.println( "gruop[1]=" +m.group(1) );
                    company=m.group(1);
                    break forElements;
                }
                //2015 (C) Company
                m = shortRegEx2.matcher(text);
                System.out.println("2015 (C) Company ?");
                while( m.find() ) {
                    System.out.println( "gruop[0]=" + m.group(0) );
                    System.out.println( "gruop[1]=" +m.group(1) );
                    company=m.group(1);
                    break forElements;
                }
                //(C) 2015 Company
                m = shortRegEx3.matcher(text);
                System.out.println("(C) 2015 Company ?");
                while( m.find() ) {
                    System.out.println( "gruop[0]=" + m.group(0) );
                    System.out.println( "gruop[1]=" +m.group(1) );
                    company=m.group(1);
                    break forElements;
                }
                //Company (C) 2015
                m = shortRegEx4.matcher(text);
                System.out.println("Company (C) 2015 ?");
                while( m.find() ) {
                    System.out.println( "gruop[0]=" + m.group(0) );
                    System.out.println( "gruop[1]=" +m.group(1) );
                    company=m.group(1);
                    break forElements;
                }
            }
            if(company!=null) {
                Matcher matchRemoveExtra = removeExtra.matcher(company);
                company = matchRemoveExtra.replaceAll("");
            }
            System.out.println( "Company=" +company );


            Date d2=new Date();

            System.out.println("Jsoup: "+(d2.getTime() - d0.getTime())+"ms");
            System.out.println("Regexp: "+(d2.getTime() - d1.getTime())+"ms");

            return;


        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
