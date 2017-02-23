package com.envista.msi.api.domain.util;

/**
 * Created by Sujit kumar on 25/01/2017.
 */
public class DashboardSroredProcParam {
    public static class DashboardFilterParams{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CONVERTED_CURRENCY_ID_PARAM = "p_currency_id";
        public static final String CONVERTED_CURRENCY_CODE_PARAM = "p_converted_curr_code";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String CARRIER_IDS_PARAM = "p_carrier_ids";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String MODE_NAMES_PARAM = "p_mode_names";
        public static final String TOP_TEN_ACCESSORIAL_PARAM =  "p_is_top_ten_accessorial";
        public static final String SERVICE_PARAM = "p_service";
        public static final String DELIVERY_FLAG_PARAM = "p_delivery_flag";
        public static final String BILLED_APPROVED_PARAM = "p_billed_approved";
        public static final String INVOICE_METHOD_PARAM = "p_invc_method";
        public static final String ORDER_MATCH_PARAM = "p_order_match";
        public static final String INVOICE_STATUS_ID_PARAM = "p_invc_sts_id";
        public static final String ACCESSORIAL_DESC_PARAM = "p_accessorial_desc";
        public static final String TAX_PARAM = "p_tax";
        public static final String SCORE_TYPE_PARAM = "p_score_type";
    }

    public static class NetSpendParams extends DashboardFilterParams {
        public static final String NET_SPEND_PARAM = "p_net_spend";
    }

    public static class TaxSpendParams extends DashboardFilterParams {
        public static final String TAX_SPEND_PARAM = "p_tax_spend";
    }

    public static class AccessorialSpendParams extends DashboardFilterParams {
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_top_accessorial_spend";
        public static final String ACCESSORIAL_SPEND_PARAM = "p_accessorial_spend";
    }

    public static class AverageSpendShipmentParam{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CONVERTED_CURRENCY_ID_PARAM = "p_convert_currency_id";
        public static final String CONVERTED_CURRENCY_CODE_PARAM = "p_converted_curr_code";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_is_top_ten_accessorial";
        public static final String AVG_SPEND_PER_SHIPMT_PARAM = "p_avg_spend_per_shipmt";
    }

    public static class AverageWeightByModeShipmtParam{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CONVERTED_WEIGHT_UNIT_PARAM = "p_converted_weight_unit";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_is_top_ten_accessorial";
        public static final String AVG_WEGT_MODE_SHIPMT_PARAM = "p_avg_wegt_by_mode_shipmt";
    }

    public static class ServiceLevelUsageAndPerformanceParams extends DashboardFilterParams {
        public static final String SERVICE_LEVEL_USAGE_PERFORMANCE_DATA = "p_serv_usage_perf_data";
    }

    public static class InboundSpendParams extends DashboardFilterParams {
        public static final String INBOUND_SPEND_DATA = "p_inbound_spend_data";
    }

    public static class OutboundSpendParams extends DashboardFilterParams {
        public static final String OUTBOUND_SPEND_DATA = "p_outbound_spend_data";
    }
    public static class InvoiceStatusCountParams extends DashboardFilterParams {
        public static final String INVOICE_COUNT_DATA_PARAM = "p_invoice_count_data";
    }

    public static class InvoiceStatusAmountParams extends DashboardFilterParams {
        public static final String INVOICE_AMOUNT_DATA_PARAM = "p_invoice_amount_data";
    }

    public static class InvoiceMethodScoreParams extends DashboardFilterParams{
        public static final String INVOICE_METHOD_SCORE_DATA_PARAM = "p_inv_method_score_data";
    }

    public static class OrderMatchParams extends DashboardFilterParams {
        public static final String ORDER_MATCH_DATA_PARAM = "p_order_match_data";
    }

    public static class BilledVsApprovedParams extends DashboardFilterParams {
        public static final String BILLED_VS_APPROVED_DATA_PARAM = "p_billed_approved_data";
    }

    public static class RecoveryAdjustmentParams extends DashboardFilterParams {
        public static final String RECOVERY_ADJUSTMENT_DATA_PARAM = "p_recov_adj_data";
    }

    public static class RecoveryServiceParams extends DashboardFilterParams {
        public static final String RECOVERY_SERVICE_DATA_PARAM = "p_recov_service_data";
    }

    public static class PackageExceptionParams extends DashboardFilterParams {
        public static final String PACKAGE_EXCEPTION_DATA_PARAM = "p_pkg_excp_data";
    }


    public static class AverageSpendShipmentByCarrierParam{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CONVERTED_CURRENCY_ID_PARAM = "p_convert_currency_id";
        public static final String CONVERTED_CURRENCY_CODE_PARAM = "p_converted_curr_code";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String ORIGINAL_FROM_DATE_PARAM = "p_original_from_date";
        public static final String ORIGINAL_TO_DATE_PARAM = "p_original_to_date";
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_is_top_ten_accessorial";
        public static final String AVG_SPEND_PER_SHIPMT_PARAM = "p_avg_spend_shipmt_bycarrier";
    }

    public static class AverageSpendShipmentByMonthParam{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CONVERTED_CURRENCY_ID_PARAM = "p_convert_currency_id";
        public static final String CONVERTED_CURRENCY_CODE_PARAM = "p_converted_curr_code";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_is_top_ten_accessorial";
        public static final String AVG_SPEND_PER_SHIPMT_PARAM = "p_avg_spend_shipmt_bymomth";
    }

    public static class ShipmentRegionParams{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CURRENCY_ID_PARAM = "p_currency_id";
        public static final String CURRENCY_CODE_PARAM = "p_converted_curr_code";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String NO_OF_LANES_PARAM = "p_no_of_lanes";
        public static final String SHIPPER_CITY = "p_shipper_city";
        public static final String RECEIVER_CITY = "p_receiver_city";
        public static final String RESULTS_DATA_PARAM = "p_results_out";
    }

    public static class ShippingLanesParams{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CURRENCY_ID_PARAM = "p_currency_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String SHIPPER_CITY = "p_shipper_city";
        public static final String SHIPPER_STATE = "p_shipper_state";
        public static final String SHIPPER_COUNTRY = "p_shipper_country";
        public static final String RECEIVER_CITY = "p_receiver_city";
        public static final String RECEIVER_STATE = "p_receiver_state";
        public static final String RECEIVER_COUNTRY = "p_receiver_country";
        public static final String RESULTS_DATA_PARAM = "p_results_out";
    }

    public static class PortLanesParams{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String CURRENCY_ID_PARAM = "p_currency_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String POL = "p_pol";
        public static final String POD = "p_pod";
        public static final String RESULTS_DATA_PARAM = "p_results_out";
    }

    public static class AverageWeightShipmentByCarrierParam{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String ORIGINAL_FROM_DATE_PARAM = "p_original_from_date";
        public static final String ORIGINAL_TO_DATE_PARAM = "p_original_to_date";
        public static final String CONVERTE_WEIGHT_UNIT_PARAM = "p_converte_weight_unit";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_is_top_ten_accessorial";
        public static final String AVG_WGT_SHIPMT_BY_CARR_PARAM = "p_avg_wegt_by_carrier";
    }
    public static class AverageWeightShipmentByMonthParam{
        public static final String DATE_TYPE_PARAM = "p_date_type";
        public static final String MODES_PARAM = "p_modes";
        public static final String SERVICES_PARAM = "p_services";
        public static final String ACCESSORIAL_NAME_PARAM = "p_accessorial_name";
        public static final String LANES_PARAM = "p_lanes_info";
        public static final String FROM_DATE_PARAM = "p_from_date";
        public static final String TO_DATE_PARAM = "p_to_date";
        public static final String CONVERTE_WEIGHT_UNIT_PARAM = "p_converte_weight_unit";
        public static final String CARRIER_ID_PARAM = "p_carrier_id";
        public static final String CUSTOMER_IDS_CSV_PARAM = "p_customer_ids";
        public static final String TOP_ACCESSORIAL_SPEND_PARAM = "p_is_top_ten_accessorial";
        public static final String AVG_WGT_SHIPMT_BY_MNTH_PARAM = "p_avg_wegt_by_month";
    }
    public static class AnnualSummaryParams extends DashboardFilterParams{
        public static final String ANNUAL_SUMMARY_DATA_PARAM = "p_annual_summary_data";
    }

    public static class MonthlySpendByModeParams extends DashboardFilterParams{
        public static final String MONTHLY_SPEND_DATA = "p_monthly_spend_data";
    }

    public static class AccountSummaryParams extends DashboardFilterParams{
        public static final String ACCOUNT_SUMMARY_DATA_PARAM = "p_acc_summ_data";
    }
}
