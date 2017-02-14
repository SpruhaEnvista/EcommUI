package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sarvesh on 2/9/2017.
 */

    @NamedStoredProcedureQueries({
            @NamedStoredProcedureQuery(name = "ZipCodesTimeZonesDto.getMapCooridantes", procedureName = "SHP_PAR_LAT_LONG_BY_CITY_PROC",
                    resultClasses = ZipCodesTimeZonesDto.class,
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_city", type = String.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_state", type = String.class),
                            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_results_out", type = Void.class)
                    })
    })

    @Entity
    public class ZipCodesTimeZonesDto implements Serializable {

        @Id
        @Column(name = "ID")
        private Long id;

        @Column(name = "LATITUDE")
        private Double latitude;

        @Column(name = "LONGITUDE")
        private Double longitude;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }


        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
}
