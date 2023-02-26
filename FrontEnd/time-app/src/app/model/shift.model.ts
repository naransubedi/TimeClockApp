import { Break } from "./break.model";
import { Lunch } from "./lunch.model";

export interface Shift {
    id: number;
    status: string;
    startDate: Date;
    endDate: Date;
    lunch: Lunch;
    break: Break;
}
