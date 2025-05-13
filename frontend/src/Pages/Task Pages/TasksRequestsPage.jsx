import TasksContainer from "../../Components/tasks/TasksContainer";

export default function TasksRequestsPage (){
    return(
        <div className="tasks-requests-page">
            <TasksContainer all = {false}/>
        </div>
    )
}