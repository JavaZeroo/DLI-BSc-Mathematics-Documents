clear; close all;

% S A B C D T
% 1 2 3 4 5 6
edges =  [...
    1 2 5;  % S->A
    1 3 7;  % S->B
    1 4 4;  % S->C
    2 3 1;  % A->B
    2 4 3;  % A->C
    3 5 3;  % B->D
    3 4 3;  % B->C
    4 6 4;  % C->T
    5 6 6;  % D->T
    5 4 1]; % D->C

names = {'S', 'A', 'B', 'C', 'D', 'T'};
G = digraph(edges(:,1), edges(:,2), edges(:,3), names);

figure;

subplot(2,1,1)
p = plot(G, 'EdgeLabel', G.Edges.Weight);
title('Original Network')
view([-90 90]);

subplot(2,1,2)
[mf, GF, cs, ct] = maxflow(G, 1, 6);
H = plot(G, 'EdgeLabel', G.Edges.Weight);
view([-90 90]);
H.EdgeLabel = {};
highlight(H, GF, 'EdgeColor', 'r', 'LineWidth', 2);
st = GF.Edges.EndNodes;
labeledge(H, st(:,1), st(:,2), GF.Edges.Weight);
title(['Max Flow from S to T (value |f| = ' num2str(mf) ') and Minimal Cut'])
highlight(H, cs, 'NodeColor', 'blue', 'MarkerSize', 10)
highlight(H, ct, 'NodeColor', 'black', 'MarkerSize', 10)