package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    @Test
    public void varastoKahdellaParametrillaTilavuus() {
        Varasto varasto2 = new Varasto(10, 10);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoKahdellaParametrillaTilavuusNeg() {
        Varasto varasto2 = new Varasto(-1, 0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoKahdellaParametrillaSaldo() {
        Varasto varasto2 = new Varasto(10, 10);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoKahdellaParametrillaSaldoNeg() {
        Varasto varasto2 = new Varasto(10, -1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenVarasto() {
        Varasto varasto3 = new Varasto(-1);
        // tilavuuden pitäisi olla nolla
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaLiikaa() {
        varasto.lisaaVarastoon(11);

        // saldon pitäisi olla maksimi
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaNegatiivinen() {
        varasto.lisaaVarastoon(-1);

        // saldon pitäisi olla nolla
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaa() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(9);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void otaNegatiivinen() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-1);

        assertEquals(1, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void string() {
        String toString = varasto.toString();
        assertTrue(toString.contains("saldo"));
    }
}