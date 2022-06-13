import React, { useState } from 'react'
import Grid from '@mui/material/Grid'
import TextField from '@mui/material/TextField'


const ValueInputMonth = (props) => {
    const [month, setMonth] = useState('')
    const [monthError, setMonthError] = useState(false)

    const hasError = (e) => {
        const month = e.target.value
        if (!month) {
            return true
        } else if (month < 1 || 12 < month) {
            return true
        } else {
            return false
        }
    }

    const gridContainerStyle = {
        verticalAlign: "center",
        border: "1px solid grey ",
        padding: "10px",
        margin: "10px",
    }

    return (
        <Grid container spaceing={1} style={gridContainerStyle}>
            <Grid item xs={3}>
                <label>{props.title}</label>
            </Grid>
            <Grid item xs={1}>
                <label>:</label>
            </Grid>
            <Grid item xs={8}>
                <TextField
                    error={monthError}
                    id="input-month"
                    label="Birth Month"
                    variant="outlined"
                    inputProps={month}
                    onChange={(e) => {
                        if (!hasError(e)) {
                            props.setValue({ ...props.value, [props.title]: e.target.value })
                            setMonth(e.target.value)
                            setMonthError(false)
                        } else {
                            setMonthError(true)
                        }
                    }}
                    style={{backgroundcole:"white"}}
                />
            </Grid>
        </Grid>
    )
};

export default ValueInputMonth;