<!doctype html>
<html lang="en">
  <head>
    <title>BookingApp</title>
	 <link rel="stylesheet" href="../../css/bootstrap.min.css">
	
	<style>
		.control-margin{
	        display: flex;
			margin-top: 20px;
		}
		.element-margin{
			margin-bottom: 5px;
		}
				
	</style>
	
	<style>
    a,abbr,acronym,address,applet,article,aside,audio,b,big,blockquote,body,canvas,caption,center,cite,code,dd,del,details,dfn,div,dl,dt,em,embed,fieldset,figcaption,figure,footer,form,h1,h2,h3,h4,h5,h6,header,hgroup,html,i,iframe,img,ins,kbd,label,legend,li,mark,menu,nav,object,ol,output,p,pre,q,ruby,s,samp,section,small,span,strike,strong,sub,summary,sup,table,tbody,td,tfoot,th,thead,time,tr,tt,u,ul,var,video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    vertical-align: baseline
}

article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {
    display: block
}

body {
    line-height: 1;
  
}

ol,ul {
    list-style: none
}

blockquote,q {
    quotes: none
}

blockquote:after,blockquote:before,q:after,q:before {
    content: "";
    content: none
}

table {
    border-collapse: collapse;
    border-spacing: 0
}

:root {
    --background: #F5F5F5;
    --brand-color: #FF6F3C;
    --color: #202020;
    --color-smooth: #606060;
    --color-white: #F5F5F5;
    --blue: #2155CD;
    --blue-50: #2155CD80;
    --blue-hover: #396be2;
    --blue-hover-50: #396be280;
    --item-bg: #EEE;
    --item-border: #D6D6D6;
    --shadow: rgba(0, 0, 0, 0.1);
    --submit-shadow: rgba(33, 85, 205, 0.25);
}

* {
    font-family: Rubik,sans-serif
}

*,:after,:before {
    box-sizing: border-box
}

html {
    font-size: 16px
}

@media screen and (max-width: 768px) {
    html {
        font-size:15px
    }
}

@media screen and (max-width: 465px) {
    html {
        font-size:14px
    }
}

body {
    min-width: 100%;
    min-height: 100%;
    height: 100vh;
    background-color:white;
}

.page-hero{
    max-height: 100vh;
    max-width: 100vw;
    overflow: hidden;
}

.login-page{
    display: flex;
    justify-content: center;
    padding: 40px;
    background-color: rgb(242, 246, 255);
}

.prewiev-hero{
    width: 50vw;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 2rem;
    background-color: var(--brand-color);
}

.prewiev-hero img{
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: .5rem;
}

.form-hero{
    width: 40vw;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 1.5rem;
    gap: 2rem;
}

.form-head{
    display:  flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    gap: 1rem;
}

.logo{
    width: 7rem;
    height: 7rem;
    padding: 1.5rem;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: var(--brand-color);
    box-shadow: 0px 0px 20px var(--shadow);
}

.login-title{
    font-size: 2rem;
    font-weight: 600;
    color: var(--color);
}

.form{
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    gap: .5rem;
    width: 400px;
    max-width: 90%;

}

.input-hero{
    width: 100%;
    height: 40px;
    position: relative;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.input-hero input{
    width: 100%;
    height: 100%;
    display: block;
    top: 0;
    left: 0; 
    position: absolute;
    font-weight: 500;
    font-size: 1rem;
    color: var(--color);
    border-radius: .5rem;
    border: .125rem solid var(--item-border);
    background: var(--background);
    padding: .5rem 3rem .5rem 1rem;
    transition: all .25s ease 0s;
}

.input-hero input:focus{
    border-color: var(--blue-50);
    outline: 0;
}

.input-name{
    left: 1rem;
    position: relative;
    display: block;
    background: var(--background);
    z-index: 2;
    transition: all .25s ease 0s;
    top: 0;
    color: var(--color-smooth);
}

.input-hero input:focus + .input-name{
    top: -1.25rem;
    color: var(--blue)
}

.input-hero input:valid + .input-name{
    top: -1.25rem;
}

.input-icon{
    position: relative;
    right: .5rem;
    z-index: 2;
    color: var(--color-smooth);
    transition: all .25s ease 0s;
}

.input-hero input:focus + .input-name + .input-icon{
    color: var(--blue)
}

.input-icon svg{
    vertical-align: middle;
    width: 1.875rem;
    height: 1.875rem;
}

.password-input{
    margin-bottom: 1.25rem;
}

.recovery-password{
    position: absolute;
    bottom: -1.15rem;
    right: 0;
    color: var(--blue)
}

.checkbox-hero{
    width: 100%;
    align-items: flex-start;
    color: var(--color-smooth);
    font-size: 1rem;
    display: flex;
    align-items: center;
    gap: .5rem
}

.checkbox-hero input{
    display: none;
}

.checkbox-cover{
    width: 1.5rem;
    height: 1.5rem;
    display: inline-block;
    background: var(--item-bg);
    border-radius: .5rem;
    border: .125rem solid var(--item-border);
    transition: all .25s ease 0s;
    cursor: pointer;
}

.checkbox-hero input:checked + .checkbox-cover{
    border-color: var(--blue);
    border-width: .4rem;
}

.content{
    display: flex;
    justify-content: center;
    margin: 30px;
    border-radius: 30px;
    padding: 20px;
    background-color:  rgb(229, 237, 254);

}

.submit-btn{
    width: 100%;
    height: 40px;
    font-weight: 500;
    font-size: 1rem;
    color: var(--color-white);
    border-radius: .5rem;
    transition: all .25s ease 0s;
    box-shadow: inset 1px 1px 3px var(--shadow);
    background: var(--blue);
    border: 1px solid var(--blue-50);
    cursor: pointer;
    box-shadow: 0px 4px 4px var(--submit-shadow);
    transition: all .25s ease 0s;
}

.submit-btn:hover{
    background: var(--blue-hover);
    border: 1px solid var(--blue-hover-50);
}

.form-bottom{
    position: fixed;
    bottom: 1rem;
    color: var(--color-smooth);
    font-size: 1rem;
    font-weight: 500;
    
}

.link{
  display: flex;
}

.form-bottom a{
    color: var(--blue)
}
.button-margin{
    align-content: center;
			margin: 10px;
            margin-top: 20px;
            height: 40px;
            width: 150px;          
            border-radius: 20px;
		}	

@media screen and (max-width: 768px) {
    .prewiev-hero{
        display: none;
    }
    .form-hero{
        width:0;
    }
}
</style>
	
  </head>
 

 

  
  
  <body class="login-page page-hero">
    <div class="content">
    <div class="login-form form-hero" style="border-right:2px solid black ;">
       
      <img src="../../img/product/parcel.jpg" alt="Preview" style="border-radius: 10px;" />
    </div>

  
    <div class="login-form form-hero">
      <div class="form-head">
        <span class="">
          <img src="../../img/product/logo.png" width="100" height="100">
            
          </svg>
        </span>
        <h1 class="login-title">CITY - XYGO APP</h1>
      </div>
  
      <form action="/login" method="post">
        <label class="input-hero" for="email">
          <input type="userId" name="userId" id="userId" required />
          <span class="input-name"> User ID </span>
          <span class="input-icon">
            <svg class="svg-icon" viewBox="0 0 20 20">
							<path d="M12.075,10.812c1.358-0.853,2.242-2.507,2.242-4.037c0-2.181-1.795-4.618-4.198-4.618S5.921,4.594,5.921,6.775c0,1.53,0.884,3.185,2.242,4.037c-3.222,0.865-5.6,3.807-5.6,7.298c0,0.23,0.189,0.42,0.42,0.42h14.273c0.23,0,0.42-0.189,0.42-0.42C17.676,14.619,15.297,11.677,12.075,10.812 M6.761,6.775c0-2.162,1.773-3.778,3.358-3.778s3.359,1.616,3.359,3.778c0,2.162-1.774,3.778-3.359,3.778S6.761,8.937,6.761,6.775 M3.415,17.69c0.218-3.51,3.142-6.297,6.704-6.297c3.562,0,6.486,2.787,6.705,6.297H3.415z"></path>
						</svg>
          </span>
        </label>
  <br/><br/>
        <label class="input-hero password-input" for="password">
          <input type="password" name="password" id="password" required />
          <span class="input-name"> Password </span>
          <span class="input-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
              <path d="M6 10V8C6 4.69 7 2 12 2C17 2 18 4.69 18 8V10" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
              <path d="M12 18.5C13.3807 18.5 14.5 17.3807 14.5 16C14.5 14.6193 13.3807 13.5 12 13.5C10.6193 13.5 9.5 14.6193 9.5 16C9.5 17.3807 10.6193 18.5 12 18.5Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
              <path d="M17 22H7C3 22 2 21 2 17V15C2 11 3 10 7 10H17C21 10 22 11 22 15V17C22 21 21 22 17 22Z" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
            </svg>
          </span>
        </label>
  
        <div class="row control-margin">
			<div>
			<button type="button" class="btn btn-primary button-margin" id="btnClear">Clear</button>
				<button type="submit" class="btn btn-primary button-margin" name="submit">Login</button>
			</div>
		</div>
        
      </form>
    </div>
    <div class="form-bottom">
        <p class="link">
            A Product of &nbsp;
             <a class="link " href="https://xyloinc.com" style="text-decoration: none" class="external"> XYLOINC</a>
        </p>
    </div>
</div>
  </body>
  
</html>
