package com.example.domain.units


val com.example.domain.model.pagerdata.Salary?.toCompactString:String
    get() {
        if (this == null) return "Зарплата не указана"
        var str = ""
        if (this.from!= null) str += "От ${this.from}"
        if (this.from!= null && this.to!=null) str += " "
        if (this.to!=null) str += "До ${this.to}"
        if (str.isNotEmpty()) str += " ${this.currency}"
        return str.ifEmpty { "Зарплата не указана" }
    }
val com.example.domain.model.vacancyinfo.Salary?.toCompactString:String
    get() {
        if (this == null) return "Зарплата не указана"
        var str = ""
        if (this.from!= null) str += "От ${this.from}"
        if (this.from!= null && this.to!=null) str += " "
        if (this.to!=null) str += "До ${this.to}"
        if (str.isNotEmpty()) str += " ${this.currency}"
        return str.ifEmpty { "Зарплата не указана" }
    }
