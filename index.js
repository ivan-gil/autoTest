function calculate(){
  var string = document.getElementById('calc-string').value;
  var firstNumber, secondNumber, operator, answer, firstIsNegative;
  if (string[0] === '-'){
    firstIsNegative= -1;
    string = string.substr(1);
  } else {
    firstIsNegative = 1
  }
  var numbers = string.split(/[+-\/\*]/);
  firstNumber = firstIsNegative * +numbers[0];
  secondNumber = +numbers[1];
  switch(true){
    case string.includes('+'):
      answer = firstNumber + secondNumber;
      break;
    case string.includes('-'):
      answer = firstNumber - secondNumber;
      break;
    case string.includes('*'):
      answer = firstNumber * secondNumber;
      break;
    case string.includes('/'):
      answer = firstNumber / secondNumber;
      break;  
    default:
      break;
   }
  document.getElementById('result').innerHTML = answer;
}
function calculateE(){
  var string = document.getElementById('calc-string').value;
  document.getElementById('result').innerHTML = eval(string);
}
document.getElementById('button').addEventListener('click', calculate);