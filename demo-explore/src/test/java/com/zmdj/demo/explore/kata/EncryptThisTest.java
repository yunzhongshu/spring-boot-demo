package com.zmdj.demo.explore.kata;

import com.zmdj.explore.kata.EncryptThis;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author zhangyunyun create on 2019/3/4
 */
public class EncryptThisTest {
    @Test
    public void exampleTests() {
        assertEquals("", EncryptThis.encryptThis(""));
        assertEquals(" 65 119esi  111dl 111lw 108dvei 105n 97n 111ka", EncryptThis.encryptThis(" A wise  old owl lived in an oak"));
        assertEquals("84eh 109ero 104e 115wa 116eh 108sse 104e 115eokp", EncryptThis.encryptThis("The more he saw the less he spoke"));
        assertEquals("84eh 108sse 104e 115eokp 116eh 109ero 104e 104dare", EncryptThis.encryptThis("The less he spoke the more he heard"));
        assertEquals("87yh 99na 119e 110to 97ll 98e 108eki 116tah 119esi 111dl 98dri", EncryptThis.encryptThis("Why can we not all be like that wise old bird"));
        assertEquals("84kanh 121uo 80roti 102ro 97ll 121ruo 104ple", EncryptThis.encryptThis("Thank you Piotr for all your help"));

        assertEquals("113rFmjL  111LeawmpFTlZzAsPwvr 68TuK 105sBjvapEicGGj 101NTkBCKaQHpk  88CrdciedzkpouX 104XhbWlxJGYgxJxLMvk  83Wd 87IhMZLMhokt 104BgQP 102rurDmSoakERdiFni 97nDtxgPuqFQPZwzstSf 80QEERHgNiHwoESL 67phQIwLHsWLKfjtUlQ 121OgzVCLEpflpG 103YmEnPFK 118UzNSZYgPNSHSc 97  75efitMSfKro 69lrqPI 105OWZVqdNgDLmDrac 103MivJkibVoo 86FW 83V 71ZREZNwRcfzOrS 121T 70FdpaycFJbPIDdZJ 110ThwKSVkvQWAVRZXlZ 78jDQxkXEOXNcJhWU 67NDJtPVoXgtH 112jEOLObQK 115BOrhcybArqhdbyssdn 98 121 90IyPnKIfUoRdh 106RJsslFbLqYuyzjh 77tEPKgYKeZOwfX 74gZvvBgnzAu 109tuY 66PPXUmsRWRJCpQW 120tUmVjpf 111i 118rCOPebwEqfAsHT 67NZwaRLevBvXIG 76PY", EncryptThis.encryptThis("qLFmjr  oreawmpFTlZzAsPwvL DKuT ijBjvapEicGGs ekTkBCKaQHpN  XXrdciedzkpouC hkhbWlxJGYgxJxLMvX  SdW WthMZLMhokI hPgQB fiurDmSoakERdiFnr afDtxgPuqFQPZwzstSn PLEERHgNiHwoESQ CQhQIwLHsWLKfjtUlp yGgzVCLEpflpO gKmEnPFY vczNSZYgPNSHSU a  KofitMSfKre EIrqPl icWZVqdNgDLmDraO goivJkibVoM VWF SV GSREZNwRcfzOrZ yT FJdpaycFJbPIDdZF nZhwKSVkvQWAVRZXlT NUDQxkXEOXNcJhWj CHDJtPVoXgtN pKEOLObQj snOrhcybArqhdbyssdB b y ZhyPnKIfUoRdI jhJsslFbLqYuyzjR MXEPKgYKeZOwft JuZvvBgnzAg mYut BWPXUmsRWRJCpQP xfUmVjpt oi vTCOPebwEqfAsHr CGZwaRLevBvXIN LYP "));
    }
}
