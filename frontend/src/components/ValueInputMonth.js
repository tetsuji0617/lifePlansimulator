import React, { useState } from 'react'
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

    return (
        <>
            <TextField
                error={monthError}
                id="input-month"
                label="Birth Month"
                valieant="outlined"
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
                margin="normal"
            />
        </>
    )
};

export default ValueInputMonth;