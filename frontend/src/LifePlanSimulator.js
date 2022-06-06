import React, { useState, useEffect } from 'react'
import { Chart, registerables } from 'chart.js';
import { Bar } from 'react-chartjs-2';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import ValueInput from './components/ValueInput'
import ValueInputYear from './components/ValueInputYear'

Chart.register(...registerables)

const LifePlanSimulator = () => {

    const [loading, setLoding] = useState(false)
    const [age, setAge] = useState(35)
    const [income, setIncome] = useState(500)
    const [expense, setExpense] = useState(20)
    const [asset, setAsset] = useState(1000)
    const [jsonData, setJsonData] = useState({});

    const [values, setValues] = useState({
        birthYear: "",
        birthMonth: "",
        income: "",
        retirementAge: ""
    });

    const inputChange = (e) => {
        const inputValue = e.value
        const inputKey = e.title
        console.log('inputChange title:' + e.title + ' value:' + e.value)
        setValues
            ({ ...values, [inputKey]: inputValue });
    }

    const expenseChanged = (e) => {
        const expenseValue = e.value
        setExpense(expenseValue)
    }

    const ageChanged = (e) => {
        const ageValue = e.value
        setAge(ageValue)
    }
    const incomeChanged = (e) => {
        console.log(e.value)
        const incomeValue = e.value
        setIncome(incomeValue)
    }
    const assetChanged = (e) => {
        console.log(e.value)
        const assetValue = e.value
        setAsset(assetValue)
    }
    useEffect(() => {
        console.log('useEffect')
        console.log(income)
        console.log(age)
        console.log(expense)
        console.log(values.birthYear)
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                income: income, age: age, expense: expense, asset: asset, household: {
                    income: income,
                    birthYear: values.birthYear,
                    birthMonth: values.birthMonth
                }
            })
        }
        fetch('http://localhost:8080/api/getLifePlan2', requestOptions)
            .then((response) => response.json())
            .then((responseJson) => {
                console.log(responseJson)
                setJsonData(responseJson)
                setLoding(true)
            })
        console.log('useEffect End')
    }, [age, income, expense, asset, values])

    if (loading) {
        console.log('render')

        return (
            <div>
                <Tabs>
                    <TabList>
                        <Tab>yourself</Tab>
                        <Tab>family</Tab>
                    </TabList>
                    <TabPanel>
                        <div>
                            <ValueInput title='age'
                                onChange={ageChanged}
                                value={age}
                                setValue={setAge} />
                            <ValueInput title='income'
                                onChange={incomeChanged}
                                value={income}
                                setValue={setIncome} />
                            <ValueInput title='expense'
                                onChange={expenseChanged}
                                value={expense}
                                setValue={setExpense} />
                            <ValueInput title='asset'
                                onChange={assetChanged}
                                value={asset}
                                setValue={setAsset} />
                            <ValueInputYear title='birthYear'
                                onChange={inputChange}
                                value={values}
                                setValue={setValues}
                            />
                            <ValueInput title='birthMonth'
                                onChange={inputChange}
                                value={values.birthMonth} />
                        </div>
                    </TabPanel>
                    <TabPanel>
                        test
                    </TabPanel>
                </Tabs>
                <Bar data={jsonData}
                    width={500}
                    height={500}
                    options={{
                        maintainAspectRatio: false, responsive: false,
                        /*                     scales: {
                                                 yAxisSales: {
                                                     scaleLabel: {
                                                         display: true,
                                                         labelString: '円'
                                                     },
                                                     id: 'yAxisSales',
                                                     position: 'left',
                                                     beginAtZero: true
                                                 },
                                                 yAxisPercentage: {
                                                     scaleLabel: {
                                                         display: true,
                                                         labelString: '円'
                                                     },
                                                     id: 'yAxisPercentage',
                                                     position: 'right',
                                                     beginAtZero: true
                                                 }
                                             }
                                             */
                    }} />
            </div>
        );
    } else {
        return (
            <div className="App-header">
                <p>Loading...</p>
            </div>
        );
    }
}


export default LifePlanSimulator;
