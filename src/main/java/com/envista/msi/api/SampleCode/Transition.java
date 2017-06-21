package com.envista.msi.api.SampleCode;

/**
 * Created by user on 6/19/2017.
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class Transition {

    Arc[] a;

    public Arc[] getA() {
        return a;
    }

    public void setA(Arc[] a) {
        this.a = a;
    }

}
