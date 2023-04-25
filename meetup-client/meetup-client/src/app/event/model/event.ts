import {Category} from "../../category/model/category";
import {User} from "../../user/model/user";

export interface Event {
  id: number,
  title: string,
  capacity: number,
  description: string,
  startRegistrationDate?: Date,
  endRegistrationDate?: Date,
  eventDate: Date,
  location: string,
  category: Category,
  creator: User,
  participants: User[]
}
