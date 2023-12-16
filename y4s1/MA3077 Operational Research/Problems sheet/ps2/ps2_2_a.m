% Problem 2.a
% O A B C D E T
% 1 2 3 4 5 6 7 
% create a matrix with edges: initial node, final node, and weight
clear; close all;

edges = [
    1 2 5;
    1 3 2;
    1 4 3;
    2 3 3;
    2 5 4;
    3 5 1;
    3 6 4;
    3 4 8;
    4 6 2;
    5 6 2;
    4 7 8;
    6 7 4;
];

node_names = {'O', 'A', 'B', 'C', 'D', 'E', 'T'};

G = graph(edges(:,1), edges(:,2), edges(:,3), node_names);

[T, steps, visited] = prims_algorithm(G, 'O');

lightgrey = [0.83, 0.83, 0.83];

figure;
disp(steps)
for i = 1:length(steps)
    subplot(3, 2, i);
    H = plot(G, 'EdgeLabel', G.Edges.Weight, 'NodeColor', lightgrey, 'EdgeColor', lightgrey);
    hold on;
    highlight(H, visited(1:i), 'NodeColor', 'b');
    highlight(H, steps{i}.Edges.EndNodes(:,1), steps{i}.Edges.EndNodes(:,2), 'EdgeColor', 'r', 'LineWidth', 1.5);
    title(sprintf('Step %d', i));
    hold off;
end

% Prim's 
function [T, steps, visited] = prims_algorithm(G, start_node)
    nodes = G.Nodes.Name;
    T = graph([], [], [], nodes);
    visited = {start_node};
    steps = {};

    while length(visited) < numnodes(G)
        min_edge = [];
        min_weight = inf;

        for v = visited
            edges = outedges(G, v{1});
            for e = edges'
                neighbor = setdiff([G.Edges.EndNodes(e, :)], v);
                if ~ismember(neighbor, visited)
                    weight = G.Edges.Weight(e);
                    if weight < min_weight
                        min_edge = [v, neighbor];
                        min_weight = weight;
                    end
                end
            end
        end

        T = addedge(T, min_edge{1}, min_edge{2}, min_weight);
        visited{end+1} = min_edge{2};
        steps{end+1} = T;
    end
end