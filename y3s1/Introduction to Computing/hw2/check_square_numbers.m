function ret = check_square_numbers(a, b)
    GLB = ceil(sqrt(a));
    if GLB^2 >= b
        ret = "There are no square numbers";
        return
    end
    ret = [];
    while GLB^2 <= b
        ret = [ret GLB];
        GLB = GLB + 1;
    end
end