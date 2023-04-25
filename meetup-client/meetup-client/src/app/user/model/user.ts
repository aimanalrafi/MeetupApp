import {Event} from "../../event/model/event";

export interface User{

  id: number,
  email: string,
  username: string,
  // password: string,
  joinedEvents?: Array<Event>
}
