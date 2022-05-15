import React from 'react';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';


ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

function chartJsSample() {

    const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

    const data = {
        labels,
        datasets: [
            {
                label: 'Dataset 1',
                data: ["100","100","100","100","100","1000","1000"],
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
            },
            {
                label: 'Dataset 2',
                data: ["2000","100","100","100","100","1000","1000"],
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            }
        ]
    }

    return <Bar data={data} data={data}
  width={300}
  height={300}
  options={{ maintainAspectRatio: false, responsive: false }}/>
}

export default chartJsSample;