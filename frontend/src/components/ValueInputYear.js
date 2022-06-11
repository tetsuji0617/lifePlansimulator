import React, { useState } from 'react'
import TextField from '@mui/material/TextField'
import Grid from '@mui/material/Grid'

const ValueInputYear = (props) => {
    const [year, setYear] = useState('')
    const [yearError, setYearError] = useState(false)

    const hasError = (e) => {
        const year = e.target.value
        if (!year) {
            return true
        } else if (year.length < 4) {
            return true
        } else if (year < 1920 || 2022 < year) {
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
        <Grid container spacing={1} style={gridContainerStyle}>
            <Grid item xs={3}>
                <label>{props.title}</label>
            </Grid>
            <Grid item xs={1}>
                <label>:</label>
            </Grid>
            <Grid item xs={8}>
                <TextField
                    error={yearError}
                    id="input-year"
                    label="Birth Year"
                    valiant="outlined"
                    inputProps={year}
                    onChange={(e) => {
                        if (!hasError(e)) {
                            props.setValue({ ...props.value, [props.title]: e.target.value })
                            setYear(e.target.value)
                            setYearError(false)
                        } else {
                            setYearError(true)
                        }
                    }}
                    margin="dense" />
            </Grid>
        </Grid>
    )
};

export default ValueInputYear;


