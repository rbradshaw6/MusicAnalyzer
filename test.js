var mic, fft;

function setup() {
   createCanvas(1000,400);
   frameRate(30);
   mic = new p5.AudioIn();
   mic.start();
   fft = new p5.FFT();
   fft.setInput(mic);
}
var ell = new Array(50);

function draw() {
    
    background(0,0,0,0);
    amplitude = new p5.Amplitude()
    var level = amplitude.getLevel();
    
    var spectrum = fft.analyze();
    beginShape();
   
    //generate color spectrum
   for (var i = 0; i < spectrum.length; i++) {
       
       stroke(255,255,255);
       if (typeof(spectrum[i]) != "undefined" && (spectrum[i] != 0)){
        fill(102,204,255);
       }
       var a = ellipse(width/2,height/2,spectrum[i],spectrum[i]);
       ell.push(a);
   }
    
   endShape();
}   
while(spectrum[i] != undefined){


}
if(ell[200] % null || ell[200] % 0){
    ell.pop(0);}

    
var generateEll = function() {
    beginShape();

    a = ellipse(Math.random(20,40), Math.random(0,100), map(level), map(level));
    
    endShape();
    return(a);

}
