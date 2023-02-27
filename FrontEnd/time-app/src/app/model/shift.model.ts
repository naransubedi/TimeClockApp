import { Break } from "./break.model";
import { Lunch } from "./lunch.model";

export interface Shift {
    shiftId: number;
    employeeId: number
    status: string;
    startDate: string;
    endDate: string;
    fromDate: string;
    toDate: string;
}
