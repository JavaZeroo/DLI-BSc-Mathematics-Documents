

syms f(x)
f(x) = @(x)tan(x)-x;
root = mybisection_while(f(x), 4, 5, 0.001)

function root = mybisection_while(f,a,b,tol) 
guess_int = [a b];
L=1;
while abs(L) > tol
    root = (guess_int(1)+guess_int(2))/2;
    L = f(root);
    if f(guess_int(1))*f(root) < 0
        guess_int=[guess_int(1) root];
    else
        guess_int=[root guess_int(2)];
    end
end
end