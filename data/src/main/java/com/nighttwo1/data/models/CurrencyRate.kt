package com.nighttwo1.data.models

import com.nighttwo1.domain.model.ExchangeRate
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CurrencyRate(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<Rates, Double>
)

@Serializable
enum class Rates(val currencyCode: String) {
    AED("AED"),
    AFN("AFN"),
    ALL("ALL"),
    AMD("AMD"),
    ANG("ANG"),
    AOA("AOA"),
    ARS("ARS"),
    AUD("AUD"),
    AWG("AWG"),
    AZN("AZN"),
    BAM("BAM"),
    BBD("BBD"),
    BDT("BDT"),
    BGN("BGN"),
    BHD("BHD"),
    BIF("BIF"),
    BMD("BMD"),
    BND("BND"),
    BOB("BOB"),
    BRL("BRL"),
    BSD("BSD"),
    BTC("BTC"),
    BTN("BTN"),
    BWP("BWP"),
    BYN("BYN"),
    BYR("BYR"),
    BZD("BZD"),
    CAD("CAD"),
    CDF("CDF"),
    CHF("CHF"),
    CLF("CLF"),
    CLP("CLP"),
    CNY("CNY"),
    COP("COP"),
    CRC("CRC"),
    CUC("CUC"),
    CUP("CUP"),
    CVE("CVE"),
    CZK("CZK"),
    DJF("DJF"),
    DKK("DKK"),
    DOP("DOP"),
    DZD("DZD"),
    EGP("EGP"),
    ERN("ERN"),
    ETB("ETB"),
    EUR("EUR"),
    FJD("FJD"),
    FKP("FKP"),
    GBP("GBP"),
    GEL("GEL"),
    GGP("GGP"),
    GHS("GHS"),
    GIP("GIP"),
    GMD("GMD"),
    GNF("GNF"),
    GTQ("GTQ"),
    GYD("GYD"),
    HKD("HKD"),
    HNL("HNL"),
    HRK("HRK"),
    HTG("HTG"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    IMP("IMP"),
    INR("INR"),
    IQD("IQD"),
    IRR("IRR"),
    ISK("ISK"),
    JEP("JEP"),
    JMD("JMD"),
    JOD("JOD"),
    JPY("JPY"),
    KES("KES"),
    KGS("KGS"),
    KHR("KHR"),
    KMF("KMF"),
    KPW("KPW"),
    KRW("KRW"),
    KWD("KWD"),
    KYD("KYD"),
    KZT("KZT"),
    LAK("LAK"),
    LBP("LBP"),
    LKR("LKR"),
    LRD("LRD"),
    LSL("LSL"),
    LTL("LTL"),
    LVL("LVL"),
    LYD("LYD"),
    MAD("MAD"),
    MDL("MDL"),
    MGA("MGA"),
    MKD("MKD"),
    MMK("MMK"),
    MNT("MNT"),
    MOP("MOP"),
    MRO("MRO"),
    MUR("MUR"),
    MVR("MVR"),
    MWK("MWK"),
    MXN("MXN"),
    MYR("MYR"),
    MZN("MZN"),
    NAD("NAD"),
    NGN("NGN"),
    NIO("NIO"),
    NOK("NOK"),
    NPR("NPR"),
    NZD("NZD"),
    OMR("OMR"),
    PAB("PAB"),
    PEN("PEN"),
    PGK("PGK"),
    PHP("PHP"),
    PKR("PKR"),
    PLN("PLN"),
    PYG("PYG"),
    QAR("QAR"),
    RON("RON"),
    RSD("RSD"),
    RUB("RUB"),
    RWF("RWF"),
    SAR("SAR"),
    SBD("SBD"),
    SCR("SCR"),
    SDG("SDG"),
    SEK("SEK"),
    SGD("SGD"),
    SHP("SHP"),
    SLE("SLE"),
    SLL("SLL"),
    SOS("SOS"),
    SRD("SRD"),
    STD("STD"),
    SYP("SYP"),
    SZL("SZL"),
    THB("THB"),
    TJS("TJS"),
    TMT("TMT"),
    TND("TND"),
    TOP("TOP"),
    TRY("TRY"),
    TTD("TTD"),
    TWD("TWD"),
    TZS("TZS"),
    UAH("UAH"),
    UGX("UGX"),
    USD("USD"),
    UYU("UYU"),
    UZS("UZS"),
    VEF("VEF"),
    VES("VES"),
    VND("VND"),
    VUV("VUV"),
    WST("WST"),
    XAF("XAF"),
    XAG("XAG"),
    XAU("XAU"),
    XCD("XCD"),
    XDR("XDR"),
    XOF("XOF"),
    XPF("XPF"),
    YER("YER"),
    ZAR("ZAR"),
    ZMK("ZMK"),
    ZMW("ZMW"),
    ZWL("ZWL");

    companion object {
        fun fromCurrencyCode(currencyCode: String): Rates? {
            return entries.find { rates ->
                rates.currencyCode == currencyCode
            }
        }
    }
}

fun CurrencyRate.toDomain(to: Currency): ExchangeRate {
    return ExchangeRate(
        from = Currency.getInstance(base),
        to = to,
        rate = rates[Rates.fromCurrencyCode(to.currencyCode)] ?: 0.0
    )
}
