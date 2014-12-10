//
// THIS IS FOR COMMON ANODE LEDS! They current SINK, which means 0
// is ON. 

class RGB
{
   r = 1
   g = 0
   b = 0
   
   rRate = 0.5;
   gRate = 0.5;
   bRate = 0.5;
   
   cycled = 0;
   
   constructor()
   {
       this.initRandom();
   }
   
   function initRandom() 
   {
       this.r = 1.0 * math.rand() / RAND_MAX;
       this.g = 1.0 * math.rand() / RAND_MAX;
       this.b = 1.0 * math.rand() / RAND_MAX;
       
       initRandomColorRampRates();
   }
   
   function initRandomColorRampRates()
   {
       
       this.rRate = (0.1 * math.rand() / RAND_MAX) - 0.05;
       this.gRate = (0.1 * math.rand() / RAND_MAX) - 0.05;
       this.bRate = (0.1 * math.rand() / RAND_MAX) - 0.05;
      // server.log("Rates: " + this.rRate + ", " + this.gRate + ", " + this.bRate);
   }
   
   function cycle() 
   {
       
       this.r += rRate;
       this.g += gRate;
       this.b += bRate;
       
       if (this.r > 1.0 || this.r < 0.0) {
           this.rRate *= -1.0;
           this.cycled += 1;
       }
       if (this.g > 1.0 || this.g < 0.0) {
           this.gRate *= -1.0;
           this.cycled += 1;
       }
       if (this.b > 1.0 || this.b < 0.0) {
           this.bRate *= -1.0;
           this.cycled += 1;
       }
       
       if (this.cycled > 50) {
           this.cycled = 0;
           this.initRandomColorRampRates();
       }
   }
}

class RGBLED
{
   redPin = null;
   bluePin = null;
   greenPin = null;
   
   constructor(redPin, greenPin, bluePin)
   {
       this.redPin = redPin;
       this.bluePin = bluePin;
       this.greenPin = greenPin;
       
       redPin.configure(PWM_OUT, 1.0/400.0, 0.0);
       greenPin.configure(PWM_OUT, 1.0/400.0, 0.0);
       bluePin.configure(PWM_OUT, 1.0/400.0, 0.0);
       
       setLevels(1, 1, 1); // 1 == OFF, it's a SINK.
       
       server.log("Constructed");
   }
   
   function setLevels(r, g, b) 
   {
       //server.log("Setting level to: " + r + ", " + g + ", " + b);
       this.redPin.write(1.0 - r);
       this.greenPin.write(1.0 - g);
       this.bluePin.write(1.0 - b);
   }
   
   function setColor(rgb)
   {
       this.setLevels(rgb.r, rgb.g, rgb.b);
   }
}



led <- RGBLED(hardware.pin7, hardware.pin8, hardware.pin9);

rgb <- RGB();

function pulse() 
{
   led.setColor(rgb);
   rgb.cycle();

// schedule the loop to run again:
imp.wakeup(0.01, pulse);
}

// And, begin.
pulse();

