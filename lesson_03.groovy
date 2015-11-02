import groovy.transform.ToString

// import java.math.MathContext

BigDecimal.metaClass.round = { int n ->
    setScale(n, BigDecimal.ROUND_HALF_UP)

//    MathContext mc = new MathContext(n);
//    round(mc)

}

interface Validator {
    boolean validate()
}

@ToString(includeNames=true,includeFields=true,excludes="address,consumer,edrpo,mfo,settlementAccount")
abstract class Communal implements Validator{
    String title
    String address
    String account
    String consumer
    String edrpo
    String mfo
    String settlementAccount
    Date date
    BigDecimal payment
}

@ToString(includeNames=true,includeSuper=true)
class Gas extends Communal {
    Integer occupant
    BigDecimal costMetroCubo
    BigDecimal norma

    @Override
    boolean validate() {
        def p = (norma * occupant * costMetroCubo).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class Electricity extends Communal {
    Integer kW
    BigDecimal tarif

    @Override
    boolean validate() {
        def p = (kW * tarif).round(2)
       p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class WaterDelivery extends Communal {
    Integer occupant
    BigDecimal metroCubo
    BigDecimal tarif

    @Override
    boolean validate() {
        def p = (occupant * tarif * metroCubo).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class WaterDerivation extends Communal {
    Integer occupant
    BigDecimal metroCubo
    BigDecimal tarif

    @Override
    boolean validate() {
        def p = (occupant * tarif * metroCubo).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class Volia extends Communal {

    @Override
    boolean validate() {
        return true
    }
}

@ToString(includeNames=true,includeSuper=true)
class Syb extends Communal {
    BigDecimal area
    BigDecimal tarif

    @Override
    boolean validate() {
        def p = (area * tarif).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class Clean extends Communal {
    Integer occupant
    BigDecimal tarif

    @Override
    boolean validate() {
        def p = (occupant * tarif).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class Heating extends Communal {
    BigDecimal area
    BigDecimal costSquareMeter

    @Override
    boolean validate() {
        def p = (area * costSquareMeter).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class HotWaterDelivery extends Communal {
    Integer occupant
    BigDecimal norma
    BigDecimal costMetroCubo

    @Override
    boolean validate() {
        def p = (occupant * norma * costMetroCubo).round(2)
        p == payment
    }
}

@ToString(includeNames=true,includeSuper=true)
class Telecom extends Communal {
    String phoneNumber

    @Override
    boolean validate() {
        return true
    }
}

def address = 'Черкаси, вул. Сєдова, буд. 1, кв. 59'
def account = '10232059'
def consumer = 'Шкляр Ігор Анатолійович'
def date = new Date(2015, 7, 1)

def gas = new Gas( title: 'ТОВ \"ЧЕРКАСИГАЗ ЗБУТ\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '39672471',
        mfo: '354507',
        settlementAccount: '26030301127727',
        date : date,
        payment: 21.56,
        occupant: 1,
        costMetroCubo: 7.188,
        norma: 3.0
)

def electricity = new Electricity( title: 'ПАТ \"Черкасиобленерго\"',
        address: address,
        account: '02-44-059',
        consumer: consumer,
        edrpo: '25204608',
        mfo: '354507',
        settlementAccount: '26037300182',
        date : date,
        payment: 28.55,
        kW: 78,
        tarif: 0.366
)

def waterDelivery = new WaterDelivery( title: 'КП \"Черкасиводоканал\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '03357168',
        mfo: '354347',
        settlementAccount: '26003060347905',
        date : date,
        payment: 23.56,
        occupant: 1,
        metroCubo: 5.5,
        tarif: 4.284
)

def waterDerivation = new WaterDerivation( title: 'КП \"Черкасиводоканал\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '03357168',
        mfo: '351005',
        settlementAccount: '26003317673900',
        date : date,
        payment: 36.83,
        occupant: 1,
        metroCubo: 9.0,
        tarif: 4.092
)

def volia = new Volia( title: 'ТОВ \"Воля-Кабель\"',
        address: address,
        account: '1254762',
        consumer: consumer,
        edrpo: '30777913',
        mfo: '351005',
        settlementAccount: '26001062793524',
        date : date,
        payment: 68.0
)

def syb = new Syb( title: 'КП \"Придніпровська СУБ\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '36701792',
        mfo: '354347',
        settlementAccount: '26004060283488',
        date : date,
        payment: 142.95,
        area: 51.0,
        tarif: 2.803
)

def clean = new Clean( title: 'КП \"Черкаська служба чистоти\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '03328652',
        mfo: '354347',
        settlementAccount: '26004060191291',
        date : date,
        payment: 7.86,
        occupant: 1,
        tarif: 7.86
)

def heating = new Heating( title: 'КП ТМ \"Черкаситеплокомуненерго\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '02082522',
        mfo: '354507',
        settlementAccount: '26038404106766',
        date : date,
        payment: 0.0,
        area: 50.4,
        costSquareMeter: 0.0
)

def hotWaterDelivery = new HotWaterDelivery( title: 'КП ТМ \"Черкаситеплокомуненерго\"',
        address: address,
        account: account,
        consumer: consumer,
        edrpo: '02082522',
        mfo: '354507',
        settlementAccount: '26038404106766',
        date : date,
        payment: 156.49,
        occupant: 1,
        norma: 3.5,
        costMetroCubo: 44.71
)

def telecom = new Telecom( title: 'ПАТ \"Укртелеком\"',
        address: address,
        account: '7133000002317690',
        consumer: consumer,
        edrpo: '01181877',
        mfo: '380805',
        settlementAccount: '26000439884',
        date : date,
        payment: 38.36,
        phoneNumber: '(0472)735873'

)
def communals = []
communals << gas << electricity << waterDelivery << waterDerivation << volia << syb << clean << heating << hotWaterDelivery << telecom

communals.each {
    println it
    println "validate() - ${it.validate()}"
    println()
}