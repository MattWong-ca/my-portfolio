//Adds random fun fact about me to the page inside funFactContainer
function funFactsAboutMe() {
  const funFacts =
      [' huge Toronto Raptors and Blue Jays fan! 🏀 ⚾',
        ' iOS Developer @ theScore! 📱',
        ' budding entrepreneur! 🚀',
        ' Web3 enthusiast! 🔮'];

    const funFact = funFacts[Math.floor(Math.random() * funFacts.length)];
    
    const funFactContainer = document.getElementById('funFacts-container');
    funFactContainer.innerText = funFact;
}

//Adds current server time to the page inside dateContainer
async function showServerTime() {
    const responseFromServer = await fetch('/date');
    const textFromResponse = await responseFromServer.text();

    const dateContainer = document.getElementById('date-container');
    dateContainer.innerText = textFromResponse;
}

//Adds JSON data to the page inside jsonContainer
async function fetchJSON() {
    // Fetches JSON from server and turns it into JSON
    const responseFromServer = await fetch('/JSONarray');
    const jsonArray = await responseFromServer.json();

    // Prints out entire JSON array as well as 2nd element into console
    console.log(jsonArray);
    console.log(jsonArray[1]);

    // Sets text in jsonContainer to be entire JSON array
    const jsonContainer = document.getElementById('json-container');
    jsonContainer.innerText = jsonArray;
}

//Lists out form responses by iterating through ArrayList
async function fetchResponseList() {
    // Fetches JSON from server and turns it into JSON
    const responseFromServer = await fetch('/form-responses');
    const responsesList = await responseFromServer.json();

    // Prints out entire ArrayList and its 1st message
    console.log(responsesList);
    console.log(responsesList[0].message)

    // Sets text in formContainer to be entire ArrayList of messages
    const formContainer = document.getElementById('form-container');
    for (let i = 0; i < responsesList.length; i++) {
        formContainer.innerText += responsesList[i].message + "💬";
    }
}

// Not used, will uncomment when I have time to re-add

// function loadTasks() {
//     fetch('/list-tasks').then(response => response.json()).then((tasks) => {
//         const taskListElement = document.getElementById('task-list');
//         tasks.forEach((task) => {
//             taskListElement.appendChild(createTaskElement(task));
//         })
//     });
// }
// Creates an element that represents a task, including its delete button
// function createTaskElement(task) {
//     const taskElement = document.createElement('li');
//     taskElement.className = 'task';

//     const titleElement = document.createElement('span');
//     titleElement.innerText = task.title;

//     const deleteButtonElement = document.createElement('button');
//     deleteButtonElement.innerText = 'Delete';
//     deleteButtonElement.addEventListener('click', () => {
//         deleteTask(task);

        // Remove the task from the DOM.
//         taskElement.remove();
//     });

//     taskElement.appendChild(titleElement);
//     taskElement.appendChild(deleteButtonElement);
//     return taskElement;
// }

/** Tells the server to delete the task. */
// function deleteTask(task) {
//     const params = new URLSearchParams();
//     params.append('id', task.id);
//     fetch('/delete-task', { method: 'POST', body: params });
// }


//JS for typing effect (source: https://css-tricks.com/snippets/css/typewriter-effect/)
var TxtType = function (el, toRotate, period) {
    this.toRotate = toRotate;
    this.el = el;
    this.loopNum = 0;
    this.period = parseInt(period, 10) || 2000;
    this.txt = '';
    this.tick();
    this.isDeleting = false;
};
TxtType.prototype.tick = function () {
    var i = this.loopNum % this.toRotate.length;
    var fullTxt = this.toRotate[i];

    if (this.isDeleting) {
        this.txt = fullTxt.substring(0, this.txt.length - 1);
    } else {
        this.txt = fullTxt.substring(0, this.txt.length + 1);
    }

    this.el.innerHTML = '<span class="wrap">' + this.txt + '</span>';

    var that = this;
    var delta = 200 - Math.random() * 100;

    if (this.isDeleting) { delta /= 2; }

    if (!this.isDeleting && this.txt === fullTxt) {
        delta = this.period;
        this.isDeleting = true;
    } else if (this.isDeleting && this.txt === '') {
        this.isDeleting = false;
        this.loopNum++;
        delta = 500;
    }

    setTimeout(function () {
        that.tick();
    }, delta);
};
window.onload = function () {
    var elements = document.getElementsByClassName('typewrite');
    for (var i = 0; i < elements.length; i++) {
        var toRotate = elements[i].getAttribute('data-type');
        var period = elements[i].getAttribute('data-period');
        if (toRotate) {
            new TxtType(elements[i], JSON.parse(toRotate), period);
        }
    }
    // INJECT CSS
    var css = document.createElement("style");
    css.type = "text/css";
    css.innerHTML = ".typewrite > .wrap { border-right: 0.08em solid black}";
    document.body.appendChild(css);
};