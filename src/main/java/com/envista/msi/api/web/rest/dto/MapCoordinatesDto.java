package com.envista.msi.api.web.rest.dto;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sarvesh on 2/9/2017.
 */

    @NamedStoredProcedureQueries({
            @NamedStoredProcedureQuery(name = "MapCoordinatesDto.getMapCooridantes", procedureName = "SHP_PAR_LAT_LONG_BY_ADD_PROC",
                    resultClasses = MapCoordinatesDto.class,
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
                            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_results_out", type = Void.class)
                    }),
            @NamedStoredProcedureQuery(name = "MapCoordinatesDto.insertMapCooridantes", procedureName = "SHP_PAR_INS_MAP_LAT_LONG_PROC",
                    resultClasses = MapCoordinatesDto.class,
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_address", type = String.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_latitude", type = Double.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_longitude", type = Double.class),
                    })
    })

    @Entity
    public class MapCoordinatesDto implements Serializable {

        @Id
        @Column(name = "ID")
        private Long id;

        @Column(name = "ADDRESS")
        private String address;

        @Column(name = "LATITUDE")
        private Double latitude;

        @Column(name = "LONGITUDE")
        private Double longitude;

        public MapCoordinatesDto() {

        }

        public MapCoordinatesDto ( Long id,String address, Double latitude, Double longitude) {
            this.id = id;
            this.setAddress(address);
            this.setLatitude(latitude);
            this.setLongitude(longitude);
        }

        public MapCoordinatesDto ( String address, Double latitude, Double longitude) {
            this.setAddress(address);
            this.setLatitude(latitude);
            this.setLongitude(longitude);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
