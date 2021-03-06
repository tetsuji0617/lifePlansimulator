import React, { useState, useEffect } from 'react'
import { Chart, registerables } from 'chart.js';
import { Bar } from 'react-chartjs-2';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import ValueInput from './components/ValueInput'
import Grid from '@mui/material/Grid'
import * as Styles from './components/style'

Chart.register(...registerables)

const LifePlanSimulator = () => {

    const [loading, setLoding] = useState(false)
    const [age, setAge] = useState(35)
    const [income, setIncome] = useState(500)
    const [expense, setExpense] = useState(20)
    const [asset, setAsset] = useState(1000)
    const [jsonData, setJsonData] = useState({});

    const [values, setValues] = useState({
        age: "",
        birthYear: "1990",
        birthMonth: "01",
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
    }, [age, income, expense, asset, values])

    if (loading) {
        return (
            <Grid spacing={2} style={Styles.gridBody}>
                <Tabs style={Styles.tabBody}>
                    <TabList>
                        <Tab>??????</Tab>
                        <Tab>???????????????</Tab>
                        <Tab>??????</Tab>
                        <Tab>?????????</Tab>
                        <Tab>??????</Tab>
                        <Tab>??????</Tab>
                    </TabList>
                    <TabPanel>
                        <Grid container spaceing={1} style={Styles.gridContainer}>
                            <Grid item xs={12} style={Styles.gridSectionTitle}>
                                ??????????????????????????????????????????
                            </Grid>
                            <Grid item xs={8}>
                                <ValueInput title='??????'
                                    onChange={ageChanged}
                                    value={age}
                                    setValue={setAge} />
                            </Grid>
                            <Grid item xs={8}>
                                <ValueInput title='??????(??????)'
                                    onChange={incomeChanged}
                                    value={income}
                                    setValue={setIncome} />
                            </Grid>
                            <Grid item xs={8}>
                                <ValueInput title='??????'
                                    onChange={assetChanged}
                                    value={asset}
                                    setValue={setAsset} />
                            </Grid>
                            <Grid item xs={8}>
                                <ValueInput title='?????????(1???????????????)'
                                    onChange={expenseChanged}
                                    value={expense}
                                    setValue={setExpense} />
                            </Grid>
                        </Grid>
                    </TabPanel>
                    <TabPanel>
                        <Grid container spaceing={1} style={Styles.gridContainer}>
                            <Grid item xs={12} style={Styles.gridSectionTitle}>
                                ????????????????????????????????????????????????
                            </Grid>
                            <Grid item xs={8}>
                                <ValueInput title='??????'
                                    onChange={ageChanged}
                                    value={age}
                                    setValue={setAge} />
                            </Grid>
                            <Grid item xs={8}>
                                <ValueInput title='??????(??????)'
                                    onChange={incomeChanged}
                                    value={income}
                                    setValue={setIncome} />
                            </Grid>
                        </Grid>
                    </TabPanel>
                    <TabPanel>
                        test
                    </TabPanel>
                    <TabPanel>
                        <Grid item xs={12} style={Styles.gridSectionTitle}>
                            ?????????????????????????????????????????????
                        </Grid>
                        <Grid item xs={8}>
                            <ValueInput title='expense'
                                onChange={expenseChanged}
                                value={expense}
                                setValue={setExpense} />
                        </Grid>
                    </TabPanel>
                    <TabPanel>
                    </TabPanel>
                    <TabPanel>


                    </TabPanel>
                </Tabs>
                <Bar data={jsonData}
                    width={800}
                    height={400}
                    options={{
                        maintainAspectRatio: false, responsive: false,
                        /*                     scales: {
                                                 yAxisSales: {
                                                     scaleLabel: {
                                                         display: true,
                                                         labelString: '???'
                                                     },
                                                     id: 'yAxisSales',
                                                     position: 'left',
                                                     beginAtZero: true
                                                 },
                                                 yAxisPercentage: {
                                                     scaleLabel: {
                                                         display: true,
                                                         labelString: '???'
                                                     },
                                                     id: 'yAxisPercentage',
                                                     position: 'right',
                                                     beginAtZero: true
                                                 }
                                             }
                                             */
                    }} />
            </Grid>
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
