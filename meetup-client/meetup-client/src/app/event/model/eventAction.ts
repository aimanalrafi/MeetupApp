import {Category} from "../../category/model/category";
import {User} from "../../user/model/user";

export interface EventAction {
  id: number,
  title: string,
  capacity: number,
  description: string,
  startRegistrationDate?: Date,
  endRegistrationDate?: Date,
  eventDate: Date,
  location: string,
  categoryId: number,
  creatorId: number
}
