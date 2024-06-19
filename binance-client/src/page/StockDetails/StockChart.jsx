import React, { useState } from 'react'
import ReactApexChart from 'react-apexcharts';

const timeSeries = [
    {
        keyword: "DIGITAL_CURRENCY_DAILY",
        key: "Time Series (Daily)",
        lable: "1 Day",
        value: 1,
    },
    {
        keyword: "DIGITAL_CURRENCY_WEEKLY",
        key: "Weekly Time Series",
        lable: "1 Week",
        value: 7,
    },
    {
        keyword: "DIGITAL_CURRENCY_MONTHLY",
        key: "Monthly Time Series",
        lable: "1 Month",
        value: 30,
    },
    {
        keyword: "DIGITAL_CURRENCY_MONTHLY_3",
        key: "3 Month Time Series",
        lable: "3 Month",
        value: 90,
    },
    {
        keyword: "DIGITAL_CURRENCY_MONTHLY_6",
        key: "6 Month Time Series",
        lable: "6 Month",
        value: 180,
    },
    {
        keyword: "DIGITAL_CURRENCY_YEARLY",
        key: "Yearly Time Series",
        lable: "1 year",
        value: 365,
    },
];

export const StockChart = () => {
    const [options] = useState({
        chart: {
            id: "area-datetime",
            type: "area",
            height: 350,
            zoom: {
                autoScaleYaxis: true,
            },
        },
        annotations: {
            // your annotations
        },
        dataLabels: {
            enabled: false,
        },

        xaxis: {
            type: "datetime",
            //   min: new Date('01 Dec 2023').getTime(),
            tickAmount: 6,
        },
        colors: ["#758AA2"], // Line color
        markers: {
            colors: ["#fff"], // Dot color
            strokeColors: "#fff", // Dot border color
            strokeWidth: 1, // Dot border width
            size: 0,
            style: "hollow",
        },
        tooltip: {
            theme: "dark",
        },
        fill: {
            type: "gradient",
            gradient: {
                shadeIntensity: 1,
                opacityFrom: 0.7,
                opacityTo: 0.9,
                stops: [0, 100],
            },
        },
        grid: {
            borderColor: "#47535E", 
            strokeDashArray: 4, 
            show: true,
        },
    });
    return (
        <div>
            <div>

            </div>
            <div id='chart-timelines'>
                <ReactApexChart options={options} series={timeSeries} height={450} type='area'/>
            </div>
        </div>
    )
}
