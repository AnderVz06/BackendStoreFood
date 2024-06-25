package com.ecom.StoreFood.services.customer.credite;

import com.ecom.StoreFood.entity.DateCredite;
import org.springframework.stereotype.Service;

@Service
public class RateCalculationService {

    public Double calcularTasaEfectiva(Double tasaNominal, int periodos) {
        return Math.pow(1 + (tasaNominal / periodos), periodos) - 1;
    }

    public Double calcularTasaNominal(Double tasaEfectiva, int periodos) {
        return periodos * (Math.pow(1 + tasaEfectiva, 1.0 / periodos) - 1);
    }

    public Double calcularInteres(Double monto, Double tasa, int periodos) {
        return monto * Math.pow(1 + tasa, periodos) - monto;
    }

    public Double convertirTasaEfectivaAnualAMensual(Double tasaEfectivaAnual) {
        return Math.pow(1 + tasaEfectivaAnual, 1.0 / 12) - 1;
    }

    public Double convertirTasaNominalAefectivaMensual(Double tasaNominal, int capitalizacionPeriodos) {
        Double tasaEfectivaAnual = calcularTasaEfectiva(tasaNominal, capitalizacionPeriodos);
        return convertirTasaEfectivaAnualAMensual(tasaEfectivaAnual);
    }

    public Double convertirTasaEfectivaAnualSegunCapitalizacion(Double tasaEfectivaAnual, int periodos) {
        return Math.pow(1 + tasaEfectivaAnual, 1.0 / periodos) - 1;
    }


    public double calcularVAN(double[] flujos, double tasaDescuento) {
        double van = 0.0;
        for (int i = 0; i < flujos.length; i++) {
            van += flujos[i] / Math.pow(1 + tasaDescuento, i);
        }
        return van;
    }

    public double calcularTIR(double[] flujos) {
        double tir = 0.1; // Valor inicial de prueba
        double delta = 0.0001; // Precisión
        double van;
        do {
            van = calcularVAN(flujos, tir);
            tir += (van > 0) ? delta : -delta;
        } while (Math.abs(van) > delta);
        return tir;
    }

    public double calcularTCEA(double tir, int periodosPorAno) {
        return Math.pow(1 + tir, periodosPorAno) - 1;
    }


    public Double calcularCuotaMensual(Double montoFinanciar, Double tasaMensual, int numCuotas) {
        int cuotas = numCuotas;
        Double montFinanciar = montoFinanciar;

        // Calcular la cuota mensual usando la fórmula de la cuota fija
        double cuotaMensual = (montoFinanciar * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -cuotas));

        return cuotaMensual;
    }

}
